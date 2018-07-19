package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.PictureService;
import com.baizhi.cmfz.util.DateConvertUtil;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
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
                             HttpServletRequest request, String pictureStatus, HttpSession session) throws IOException, MyException {
        String message = "";

        String realPath = session.getServletContext().getRealPath("");
        realPath = realPath.substring(0,realPath.lastIndexOf("\\"));
        String path = realPath.substring(0,realPath.lastIndexOf("\\"));

        //String uuidName = UUID.randomUUID().toString().replace("-","");
        //String olName = myFile.getOriginalFilename();
        //String suffix = olName.substring(olName.lastIndexOf("."));
        //String picturePath = "/upload/" + uuidName + ".jpg";
        //myFile.transferTo(new File(path + picturePath));


        /**
         * FastDFS
         */
        ClientGlobal.init("fdfs_client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient client = new StorageClient(trackerServer, null);
        String[] fileId = client.upload_file(myFile.getBytes(), "png", null);
        /*for (String s : fileId) {
            System.out.println(s);
        }*/
        String ip = trackerServer.getInetSocketAddress().toString();
        String _picPath = ip.substring(0,ip.lastIndexOf(":"))+ "/" + fileId[0] + "/" + fileId[1];
        String picPath = "http://" + _picPath.substring(1);
        System.out.println(picPath);

        Picture picture = new Picture();
        picture.setPictureStatus(pictureStatus);
        picture.setPictureDescription(pictureDescription);
        picture.setPicturePath(picPath);
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
