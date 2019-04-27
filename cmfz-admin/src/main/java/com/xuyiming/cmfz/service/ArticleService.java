package com.xuyiming.cmfz.service;

import com.xuyiming.cmfz.entity.Article;

import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/10/9.
 */
public interface ArticleService {

    public Boolean addArticle(Article article);

    public Map<String,Object> queryArticle(Integer nowPage,Integer pageSize);

}
