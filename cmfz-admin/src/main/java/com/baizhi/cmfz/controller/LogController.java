package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/7/10.
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    public LogService logService;

    @RequestMapping("/findLog")
    @ResponseBody
    public Map<String,Object> selectLog(@RequestParam("page") Integer nowPage, @RequestParam("rows") Integer pageSize){
        Map<String,Object> map = logService.queryLog(nowPage,pageSize);

        for (Map.Entry<String, Object> s : map.entrySet()) {
            System.out.println(s.getKey() + "===" + s.getValue());
        }

        return map;
    }


}
