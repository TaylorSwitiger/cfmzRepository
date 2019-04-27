package com.xuyiming.cmfz.service.impl;

import com.xuyiming.cmfz.dao.LogDao;
import com.xuyiming.cmfz.entity.Log;
import com.xuyiming.cmfz.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/10/10.
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;


    public Map<String, Object> queryLog(Integer nowPage, Integer pageSize) {
        Map<String,Object> map = new HashMap<String, Object>();

        Integer count = logDao.selectCount();
        List<Log> logs = logDao.selectLog((nowPage-1)*pageSize,pageSize);

        map.put("rows",logs);
        map.put("total",count);

        return map;
    }

    public void increaseLog(Log log) {

        logDao.insertLog(log);

    }
}
