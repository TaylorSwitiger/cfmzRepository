package com.xuyiming.cmfz.controller;

import com.xuyiming.cmfz.entity.Manager;
import com.xuyiming.cmfz.entity.Menu;
import com.xuyiming.cmfz.service.ManagerService;
import com.xuyiming.cmfz.service.PictureService;
import com.xuyiming.cmfz.util.CreateValidateCodeUtil;
import com.xuyiming.cmfz.util.EncryptionUtil;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 阿斯加的酱油 on 2018/7/4.
 */
@Controller
@RequestMapping("/mgr")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/login")
    public String managerLogin(Manager manager, String enCode, boolean check,
                               HttpServletResponse response, HttpServletRequest request ,
                               HttpSession session, boolean rememberMe) throws UnsupportedEncodingException {
        //System.out.println(manager.getMgrName() + "------" + manager.getMgrPwd() + "----" + check);

        /*for (Cookie cookie : request.getCookies()) {
            System.out.println(cookie.getValue());
        }*/


        if (session.getAttribute("code").equals(enCode)) {

            if (check) {
                Cookie c1 = new Cookie("mgrName", URLEncoder.encode(manager.getMgrName(),"utf-8"));

                c1.setPath("/");
                c1.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(c1);

            } else {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("mgrName")) {
                        cookie.setValue(null);
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }

            Subject subject = SecurityUtils.getSubject();

            try {
                subject.login(new UsernamePasswordToken(manager.getMgrName(),manager.getMgrPwd(),rememberMe));
                session.setAttribute("manager",manager);
                return "main/main";
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return "redirect:/login.jsp";
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return "redirect:/login.jsp";
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return "redirect:/login.jsp";
            }
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping("/menu")
    @ResponseBody
    public Map<String, Object> gMenu(){
        Map<String, Object> map = new HashMap<>();
        List<Menu> menus = managerService.queryMenu();
       /* for (Menu menu : menus) {
            System.out.println(menu);
        }*/
       map.put("menuList", menus);
       List<String> picPath = pictureService.queryPicturePath();
       map.put("imgUrl", picPath);
        return map;
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

    @RequestMapping("/changePwd")
    public String changePassword(Manager manager) {
        Subject subject = SecurityUtils.getSubject();
        manager.setSalt(EncryptionUtil.getRandomSalt());
        manager.setMgrPwd(EncryptionUtil.getAlforithmPassword(manager.getMgrPwd(), manager.getSalt()));
        if (!managerService.changeManager(manager)) {
            return null;
        }
        subject.logout();
        return "redirect:/login.jsp";
    }

    /**
     * 在添加社员Master时级联注册Manager
     * 默认密码123456，账号是手机号
     * @return
     */
    @RequestMapping("/addManager")
    @ResponseBody
    public String addManager(Manager manager) {
        manager.setMgrId(UUID.randomUUID().toString().replace("-",""));
        manager.setSalt(EncryptionUtil.getRandomSalt());
        manager.setMgrPwd(EncryptionUtil.getAlforithmPassword(manager.getMgrPwd(), manager.getSalt()));
        if (!managerService.addManager(manager)) {
            return "ok";
        }
        return "no";
    }

    @RequestMapping("/findManager")
    @ResponseBody
    public Map<String,Object> findManagerByPage(@RequestParam("page") Integer nowPage, @RequestParam("rows")Integer pageSize,String key,String category) throws ParseException, InvalidTokenOffsetsException, IOException {
        Map<String,Object> map = new HashMap<String, Object>();

        if (key == null || key.equals("")) {
            map = managerService.queryManagerByPage(nowPage,pageSize);
        } else {
            map = managerService.queryManagerByKey(nowPage,pageSize,key,category);
        }
        return map;
    }

    @RequestMapping("/cancelManager")
    @ResponseBody
    public String cancelManager(Manager manager) {
        manager.setStatus("n");
        if (managerService.changeManager(manager)) {
            managerService.changeManagerRole(3, manager.getMgrId());
            return "ok";
        }
        return "no";
    }

    @RequestMapping("/setasManager")
    @ResponseBody
    public String setasManager(Manager manager) {
        manager.setStatus("y");
        if (managerService.changeManager(manager)) {
            managerService.changeManagerRole(2, manager.getMgrId());
            return "ok";
        }
        return "no";
    }
}
