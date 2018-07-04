package com.baizhi.testService;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.service.ManagerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 阿斯加的酱油 on 2018/7/4.
 */
public class TestService {

    @Test
    public void TestServiceone(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        ManagerService managerService= (ManagerService) ctx.getBean("managerServiceImpl");
        Manager manager= managerService.queryManager("xym","123");
        System.out.println("manager");
    }

    @Test
    public void createSalt(){

       // String salt

    }
}
