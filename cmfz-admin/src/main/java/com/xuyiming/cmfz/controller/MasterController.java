package com.xuyiming.cmfz.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.xuyiming.cmfz.entity.Manager;
import com.xuyiming.cmfz.entity.Master;
import com.xuyiming.cmfz.service.MasterService;
import com.xuyiming.cmfz.util.EncryptionUtil;
import com.xuyiming.cmfz.util.IndexMaster;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Paths;
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
    public Map<String,Object> masterByPage(@RequestParam("page") Integer nowPage, @RequestParam("rows")Integer pageSize,String key,String category) throws ParseException, InvalidTokenOffsetsException, IOException {
        Map<String,Object> map = new HashMap<String, Object>();

        if (key == null || key.equals("")) {
            map = masterService.queryMaster(nowPage,pageSize);
        } else {
            map = masterService.queryMaster(nowPage,pageSize,key,category);
//            map = IndexMaster.indexMaster(nowPage,pageSize,key,category);
        }
        return map;
    }

    @RequestMapping("/addMaster")
    @ResponseBody
    public String addMaster(Master master) throws IOException {
        String message = "";
        //System.out.println(123123);
        try {
            masterService.addMaster(master);
        } catch (Exception e) {
            if ("账户已存在".equals(e.getMessage())) {
                return "Account already exists.";
            }
        }

        /**
             * 更新索引库
             */
            List<Master> masters = masterService.queryMaster();

            /*for (Master master1 : masters) {
                System.out.println(master1);
            }*/

            FSDirectory fsDirectory = FSDirectory.open(Paths.get("G:\\index\\02"));

            IndexWriter indexWriter = new IndexWriter(fsDirectory, new IndexWriterConfig(new StandardAnalyzer()));

            for (int i = 0;i < masters.size() ;i++) {
                Document document= new Document();
                // Store.YES | NO 代表是不是要将域值保存到索引库的数据存储区
                document.add(new IntField("id",masters.get(i).getMasterId(), Field.Store.YES)); // 指定数据的编号
                document.add(new TextField("name",masters.get(i).getMasterName(), Field.Store.YES));
                document.add(new TextField("phone",masters.get(i).getMasterPhoto(), Field.Store.YES));
                document.add(new TextField("summary",masters.get(i).getMasterrSummary(), Field.Store.YES));

                indexWriter.addDocument(document);
            }
            indexWriter.flush();

            indexWriter.commit();

            message = "ok";

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

        String name = "社员信息表";
        String fileName = name + ".xls";

        //Excel文件
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("社员信息",name ),Master.class,masters);

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


    @RequestMapping("/removeMaster")
    @ResponseBody
    public String removeMaster(String masterId) throws IOException, ParseException, InvalidTokenOffsetsException {
        if (masterService.removeMaster(masterId)) {
            return "ok";
        } else {
            return "no";
        }
    }
}
