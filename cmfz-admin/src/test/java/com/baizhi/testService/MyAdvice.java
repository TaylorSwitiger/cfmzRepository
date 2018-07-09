package com.baizhi.testService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 额外功能类
 *
 * Created by 阿斯加的酱油 on 2018/7/9.
 */
@Aspect
public class MyAdvice {

    /**
     * 声明切入点表达式
     */
    //@Pointcut(value = "execution(* com.baizhi.testService.*.*(..)) || execution(* com.baizhi.service.*.*(..)")
    @Pointcut("within(com.baizhi.testService.AspectJServiceImpl)") //基于类型的aop
    public void pc(){}

    /**
     * 前置通知方法
     */
    @Before("pc()")  //标识当前的前置通知使用pc方法上声明的切入点表达式
    public void before(){
        System.out.println("----前置通知----");
    }

    /**
     * 后置通知(无论原始方法成功与否都有)
     */
    /*@After("pc()")
    public void after(){
        System.out.println("----后置通知----");
    }*/

    /**
     * 后置通知（只有原始方发成功结束才有）
     */
    @AfterReturning("pc()")
    public void after(){
        System.out.println("----后置通知----");
    }
    /**
     * 环绕通知
     */
    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable { //连接点
        System.out.println("----环绕前----");

        //将调用传递
        //obj 原始方法的返回值
        Object obj = pjp.proceed();
        System.out.println("advice"+obj);
        System.out.println("----环绕后----");
        return obj;
    }
}
