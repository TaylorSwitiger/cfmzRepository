package com.xuyiming.cmfz.service.impl;

import com.xuyiming.cmfz.dao.ArticleDao;
import com.xuyiming.cmfz.entity.Article;
import com.xuyiming.cmfz.service.ArticleService;
import com.xuyiming.cmfz.util.DateConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 阿斯加的酱油 on 2018/10/9.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    public ArticleDao articleDao;

    public Boolean addArticle(Article article) {
        boolean flag = false;

        String date = DateConvertUtil.toString(new Date());
        Date utilDate = DateConvertUtil.toUtilDate(date);
        java.sql.Date sqlDate = DateConvertUtil.toSqlDate(utilDate);

        article.setPublishDate(sqlDate);

        String id = UUID.randomUUID().toString().replace("-","");
        article.setArticleId(id);

        if (articleDao.insertArticle(article) > 0) {
            flag = true;
        }

        return flag;
    }

    public Map<String, Object> queryArticle(Integer nowPage, Integer pageSize) {
        Map<String,Object> map = new HashMap<String, Object>();

        List<Article> articles = new ArrayList<Article>();

        Integer count = articleDao.selectCount();

        articles = articleDao.selectArticle((nowPage-1)*pageSize,pageSize);

        map.put("rows",articles);
        map.put("total",count);

        return map;
    }
}
