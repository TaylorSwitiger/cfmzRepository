package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 阿斯加的酱油 on 2018/7/9.
 */
public interface ArticleDao {

    public int insertArticle(Article article);

    public List<Article> selectArticle(@Param("begin")Integer begin,@Param("size")Integer size);

    public Integer selectCount();
}
