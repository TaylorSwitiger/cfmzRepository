package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/7/9.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @RequestMapping("/addArticle")
    public String addArticle(Article article){
        String message = "";
        if (articleService.addArticle(article)) {
            message = "ok";
        } else {
            message = "no";
        }
        return message;
    }

    @RequestMapping("/findArticle")
    @ResponseBody
    public Map<String,Object> selectArticle(@RequestParam("page") Integer nowPage, @RequestParam("rows") Integer pageSize){
        Map<String,Object> map = articleService.queryArticle(nowPage,pageSize);

       /* for (Map.Entry<String, Object> s : map.entrySet()) {
            System.out.println(s.getKey() + "===" + s.getValue());
        }*/

        return map;
    }

}
