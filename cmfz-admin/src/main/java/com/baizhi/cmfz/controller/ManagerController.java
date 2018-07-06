package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.ManagerService;
import com.baizhi.cmfz.util.CreateValidateCodeUtil;
import com.baizhi.cmfz.util.DateConvertUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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

        if (session.getAttribute("code").equals(enCode)) {
            Manager mgr = managerService.queryManager(manager.getMgrName(),manager.getMgrPwd());
            if (mgr == null){
                return "login";
            }
            session.setAttribute("manager",mgr);
            return "main/main";
        }
        return "login";
    }

    @RequestMapping("/menu")
    @ResponseBody
    public List<Menu> gMenu(){
        List<Menu> menus = managerService.queryMenu();
       /* for (Menu menu : menus) {
            System.out.println(menu);
        }*/
        return menus;
    }

    @RequestMapping("/findPicture")
    @ResponseBody
    public Map<String,Object> pictureByPage(@RequestParam("page") Integer nowPage,@RequestParam("rows")Integer pageSize){
        Map<String,Object> map = managerService.queryPicture(nowPage,pageSize);
        return map;
    }

    @RequestMapping("/addPicture")
    @ResponseBody
    public String addPicture( @RequestParam("picturePath") MultipartFile myFile, String pictureDescription,
                               HttpServletRequest request,String pictureStatus,HttpSession session) throws IOException {
        String message = "";

        String realPath = session.getServletContext().getRealPath("");
        realPath = realPath.substring(0,realPath.lastIndexOf("\\"));
        String path = realPath.substring(0,realPath.lastIndexOf("\\"));

        String uuidName = UUID.randomUUID().toString().replace("-","");
        //String olName = myFile.getOriginalFilename();
        //String suffix = olName.substring(olName.lastIndexOf("."));
        String picturePath = "/upload/" + uuidName + ".jpg";
        myFile.transferTo(new File(path + picturePath));

        Picture picture = new Picture();
        picture.setPictureStatus(pictureStatus);
        picture.setPictureDescription(pictureDescription);
        picture.setPicturePath(picturePath);
        picture.setPictureDate(DateConvertUtil.toUtilDate(DateConvertUtil.toString(new Date())));
        if (managerService.addPicture(picture)){
            message = "ok";
        } else {
            message = "no";
        }
        return message;
    }

    @RequestMapping("/updatePicture")
    @ResponseBody
    public String modifyPicture( Picture picture) throws IOException {
        String message = "";
        if (managerService.modifyPicture(picture)) {
            message = "ok";
        } else {
            message = "no";
        }
        return message;
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
