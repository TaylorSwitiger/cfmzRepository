package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.service.ManagerService;
import com.baizhi.cmfz.util.CreateValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/7/4.
 */
@Controller
@RequestMapping("/mgr")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/login")
    public String managerLogin(Manager manager,String enCode,boolean check, Model model, HttpServletResponse response,
                               HttpServletRequest request , HttpSession session) throws UnsupportedEncodingException {
        //System.out.println(manager.getMgrName() + "------" + manager.getMgrPwd() + "----" + check);

        /*for (Cookie cookie : request.getCookies()) {
            System.out.println(cookie.getValue());
        }*/
        if (check) {
            Cookie c1 = new Cookie("mgrName", URLEncoder.encode(manager.getMgrName(),"utf-8"));

            c1.setPath("/");
            c1.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(c1);
            System.out.println(c1.getValue());
        }

        if (session.getAttribute("code").equals(enCode)) {
            Manager mgr = managerService.queryManager(manager.getMgrName(),manager.getMgrPwd());
            if (mgr == null){
                return "login";
            }
            session.setAttribute("manager",mgr);
            return "success";
        }
        return "login";
    }

    @RequestMapping("/getVcode")
    public void validateCode(HttpSession session, HttpServletResponse response) throws IOException {
        //创建工具对象，并生成验证码
        CreateValidateCodeUtil vCode = new CreateValidateCodeUtil(100,30,1);

        //保存到Session作用域
        session.setAttribute("code",vCode.getCode());

        //使用response获得指向客户端的流对象，并输出
        vCode.write(response.getOutputStream());

    }

}
