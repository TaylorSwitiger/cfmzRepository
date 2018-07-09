package com.baizhi.cmfz.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.cmfz.entity.Master;
import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.MasterService;
import com.baizhi.cmfz.service.PictureService;
import com.baizhi.cmfz.util.DateConvertUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
    public Map<String,Object> masterByPage(@RequestParam("page") Integer nowPage, @RequestParam("rows")Integer pageSize,String key,String category){
        Map<String,Object> map = new HashMap<String, Object>();

        if (key == null || key.equals("")) {
            map = masterService.queryMaster(nowPage,pageSize);
        } else {
            map = masterService.queryMaster(nowPage,pageSize,key,category);
        }
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

    @RequestMapping("/addMasterBatch")
    @ResponseBody
    public String addMasterBatch(MultipartFile masterFile) throws IOException {
        String message = "";

        //参数一：输入流
        //参数二：project
        //参数三：导入参数对象
        ImportParams importParams = new ImportParams();
        try {
            List<Master> masters = ExcelImportUtil.importExcel(masterFile.getInputStream(),Master.class,importParams);
            if (masterService.addMasterBatch(masters)){
                message = "ok";
            } else {
                message = "no";
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    @RequestMapping("/exportMaster")
    @ResponseBody
    public void exportMaster(HttpServletResponse response) throws IOException {
        String message = "";

        List<Master> masters = masterService.queryMaster();

        String name = "上师信息表";
        String fileName = name + ".xls";

        //Excel文件
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("RW.Doinb",name ),Master.class,masters);

        ServletOutputStream out = response.getOutputStream();

        //文件下载的响应头
        response.setContentType("application/vnd.ms-excel"); //响应类型  text/html application/json等
        response.setHeader("content-disposition","attachment;fileName=" + fileName);

        //导出 文件下载的方式
        workbook.write(out);

        out.close();
    }

    @RequestMapping("/allMaster")
    @ResponseBody
    public List<Master> allMaster(HttpServletResponse response){
        String message = "";

        List<Master> masters = masterService.queryMaster();

        return masters;
    }

}
