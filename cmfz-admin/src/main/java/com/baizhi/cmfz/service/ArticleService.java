package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Article;

import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/7/9.
 */
public interface ArticleService {

    public Boolean addArticle(Article article);

    public Map<String,Object> queryArticle(Integer nowPage,Integer pageSize);

}
