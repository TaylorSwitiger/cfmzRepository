package com.xuyiming.cmfz.controller;

import com.xuyiming.cmfz.entity.RichTextResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by 阿斯加的酱油 on 2018/7/8.
 */
@Controller
@RequestMapping("/rt ")
public class RichTextController {

    /**
     *   多文件上传
     * @param files
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public RichTextResult uploadFiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {  //MultipartFile[] 多文件上传
        RichTextResult result = new RichTextResult();
        ArrayList<String> data = new ArrayList<String>();

        try{

            //文件 ---> 服务器指定目录
            String realPath = request.getSession().getServletContext().getRealPath("");
            String uploadPath = realPath.substring(0,realPath.substring(0,realPath.lastIndexOf("\\")).lastIndexOf("\\")) + "\\upload";
            System.out.println(realPath + "====" + uploadPath);
            if (files != null && files.length != 0) {
                for (MultipartFile file : files) {
                    //将上传的文件转存到服务器中存储
                    String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
                    file.transferTo(new File(uploadPath + "\\" + fileName));

                    //将上传的图片在服务器的url响应给客户端  图片回显
                    data.add(request.getContextPath() + "/upload/" + fileName);
                }
            }
            result.setErrno(0);
            result.setData(data);
        } catch (Exception e) {
            result.setErrno(1);
            e.printStackTrace();
        }

        return result;

    }
}
