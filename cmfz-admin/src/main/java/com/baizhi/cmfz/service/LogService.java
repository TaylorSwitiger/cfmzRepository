package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/7/10.
 */

public interface LogService {

    public Map<String,Object> queryLog(Integer nowPage,Integer pageSize);

    public void increaseLog(Log log);

}
