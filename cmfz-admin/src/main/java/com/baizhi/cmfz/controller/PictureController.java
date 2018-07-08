package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.PictureService;
import com.baizhi.cmfz.util.DateConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 阿斯加的酱油 on 2018/7/8.
 */
@Controller
@RequestMapping("/pic")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/findPicture")
    @ResponseBody
    public Map<String,Object> pictureByPage(@RequestParam("page") Integer nowPage, @RequestParam("rows")Integer pageSize){
        Map<String,Object> map = pictureService.queryPicture(nowPage,pageSize);
        return map;
    }

    @RequestMapping("/addPicture")
    @ResponseBody
    public String addPicture(@RequestParam("picturePath") MultipartFile myFile, String pictureDescription,
                             HttpServletRequest request, String pictureStatus, HttpSession session) throws IOException {
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
        if (pictureService.addPicture(picture)){
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
        System.out.println(111111);
        if (pictureService.modifyPicture(picture)) {
            message = "ok";
        } else {
            message = "no";
        }
        return message;
    }

}
