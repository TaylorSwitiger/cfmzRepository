package com.baizhi.testService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 阿斯加的酱油 on 2018/7/9.
 */
public class AspectJTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:aspectj.xml");
        AspectJService aspectJService = (AspectJService) applicationContext.getBean("aspectJService");
        String sayHello = aspectJService.sayHello("zs");
        System.out.println(sayHello);
    }

}
