package com.baizhi.testService;

/**
 * Created by 阿斯加的酱油 on 2018/7/9.
 */
public class AspectJServiceImpl implements AspectJService {
    public String sayHello(String name) {
        System.out.println("---原始方法---");
        return "Hello,"+name;
    }
}
