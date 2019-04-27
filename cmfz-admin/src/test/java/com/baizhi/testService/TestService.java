package com.baizhi.testService;

import com.xuyiming.cmfz.entity.Article;
import com.xuyiming.cmfz.service.ArticleService;
import com.xuyiming.cmfz.util.EncryptionUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 阿斯加的酱油 on 2018/7/4.
 */
public class TestService {

    @Test
    public void TestServiceone(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        ArticleService articleService= (ArticleService) ctx.getBean("articleServiceImpl");

        Article art = new Article();
        art.setArticleId(UUID.randomUUID().toString().replace("-",""));
        art.setPublishDate(DateConvertUtil.toSqlDate(new Date()));
        art.setArticleName("aassa");
        art.setIntroduction("324243");
        art.setMasterId("123");
        articleService.addArticle(art);

        System.out.println("aa");
    }

    @Test
    public void createSalt(){

       String salt = EncryptionUtil.getRandomSalt(6);
       String password = EncryptionUtil.encryptionCode("123" + salt);
       System.out.println(salt + "===" + password);
       String pwd = new SimpleHash("MD5", "123456", "xym", 1024).toHex();

        System.out.println(pwd.equals("6cf78017c8fb1e771f83403013eff58e"));
    }
}
