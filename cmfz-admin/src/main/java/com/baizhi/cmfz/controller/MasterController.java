package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Master;
import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.MasterService;
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
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @RequestMapping("/findMaster")
    @ResponseBody
    public Map<String,Object> masterByPage(@RequestParam("page") Integer nowPage, @RequestParam("rows")Integer pageSize){
        Map<String,Object> map = masterService.queryMaster(nowPage,pageSize);
        return map;
    }

    @RequestMapping("/addMaster")
    @ResponseBody
    public String addMaster(Master master) throws IOException {
        String message = "";

        if (masterService.addMaster(master)){
            message = "ok";
        } else {
            message = "no";
        }
        return message;
    }

    @RequestMapping("/updateMaster")
    @ResponseBody
    public String modifyMaster( Master master) throws IOException {
        String message = "";
        if (masterService.modifyMaster(master)) {
            message = "ok";
        } else {
            message = "no";
        }
        return message;
    }

}
