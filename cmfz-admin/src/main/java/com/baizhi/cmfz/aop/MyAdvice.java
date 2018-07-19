package com.baizhi.cmfz.aop;

import com.baizhi.cmfz.entity.Log;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.service.LogService;
import com.baizhi.cmfz.util.DateConvertUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * 额外功能类
 *
 * Created by 阿斯加的酱油 on 2018/7/9.
 */
@Aspect
public class MyAdvice {

    @Autowired
    private LogService logService;

    /**
     * 声明切入点表达式
     */
    @Pointcut(value = "execution(* com.baizhi.cmfz.service.impl.*.modify*(..)) || execution(* com.baizhi.cmfz.service.impl.*.add*(..))")
    //@Pointcut("within(com.baizhi.cmfz.service.impl.ManagerServiceImpl)") //基于类型的aop
    public void pc(){}

    /**
     * 前置通知方法
     */
//    @Before("pc()")  //标识当前的前置通知使用pc方法上声明的切入点表达式
//    public void before(ProceedingJoinPoint pjp){
//        System.out.println("----前置通知----");
//
//    }

    /**
     * 后置通知(无论原始方法成功与否都有)
     */
//    @After("pc()")
//    public void after(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("----后置通知----");
//
//
//    }

    /**
     * 后置通知（只有原始方发成功结束才有）
     */
//    @AfterReturning("pc()")
//    public void after(){
//        System.out.println("----后置通知----");
//    }
    /**
     * 环绕通知
     */
    @Around("pc()")
    public void around(ProceedingJoinPoint pjp) throws Throwable { //连接点
        //System.out.println("----环绕前----");
        Log log = new Log();
        HttpSession s = (HttpSession) RequestContextHolder
                .currentRequestAttributes()
                .resolveReference(RequestAttributes.REFERENCE_SESSION);

        log.setLogId(UUID.randomUUID().toString().replace("-",""));
        Manager manager = (Manager)s.getAttribute("manager");
        log.setOperateManager(manager.getMgrName());
        log.setOperateTime(new java.sql.Date(DateConvertUtil.toUtilDate(DateConvertUtil.toString(new Date())).getTime()));

        String className = pjp.getTarget().getClass().getName();
        className = className.substring(className.lastIndexOf("."));
        String resource = className.substring(1,className.lastIndexOf("S"));
        log.setResource(resource);

        String message = "";
        for (Object o : pjp.getArgs()) {
            message += o.toString();
        }
        log.setMessage(message);

        String result = "fail";

        Object obj = null;


        try {
            obj = pjp.proceed();
            result = "success";
        } catch (Throwable throwable) {
            result = "fail";
            throwable.printStackTrace();
        }

        log.setResult(result);
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        log.setAction(method.getName());

        logService.increaseLog(log);

        //System.out.println("----环绕后----");
    }
}
