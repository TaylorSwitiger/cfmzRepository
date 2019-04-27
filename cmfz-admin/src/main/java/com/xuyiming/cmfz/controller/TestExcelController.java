package com.xuyiming.cmfz.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/10/21.
 */
@Controller
public class TestExcelController {

    @RequestMapping(value="/importUser")
    public String importUser(HttpServletRequest request, HttpServletResponse response,@RequestParam("masterFile") MultipartFile file){
        try {
            //也可以用request获取上传文件
            //MultipartFile  fileFile = request.getFile("file"); //这里是页面的name属性
            //获取后缀判断文件类型，.是特殊字符，需要转义！！！
            String[] split = file.getOriginalFilename().split("\\.");
            //转换成输入流
            InputStream is = file.getInputStream();
            //得到excel
            Workbook wb;
            if ( "xls".equals(split[1])){
                wb = new HSSFWorkbook(is);
            }else if ("xlsx".equals(split[1])){
                wb = new XSSFWorkbook(is);
            }else {
                return "文件类型错误!";
            }


            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                // 循环行Row
                for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row hssfRow = sheet.getRow(rowNum);
                    if (hssfRow != null) {
                        for (int colNum = 0; colNum < hssfRow.getPhysicalNumberOfCells(); colNum++) {
                            Cell cell= hssfRow.getCell(colNum);
                            System.out.println(cell.toString());
                            System.out.println(cell.getStringCellValue());
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
