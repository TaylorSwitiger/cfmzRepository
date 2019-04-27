package com.xuyiming.cmfz.service;

import com.xuyiming.cmfz.entity.Log;

import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/10/10.
 */

public interface LogService {

    public Map<String,Object> queryLog(Integer nowPage,Integer pageSize);

    public void increaseLog(Log log);

}
