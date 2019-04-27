package com.xuyiming.cmfz.controller;

import com.xuyiming.cmfz.entity.Article;
import com.xuyiming.cmfz.entity.Manager;
import com.xuyiming.cmfz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
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
        HttpSession session = (HttpSession) RequestContextHolder.currentRequestAttributes()
                .resolveReference(RequestAttributes.REFERENCE_SESSION);
        Object manager = session.getAttribute("manager");
        if (manager instanceof Manager) {
            article.setMasterId(((Manager) manager).getMgrName());
        }
        boolean flag = false;
        try {
          flag  = articleService.addArticle(article);
        } catch (NullPointerException npe) {
            message = "未知错误！！";
            return message;
        }
        if (flag) {
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
