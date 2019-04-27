package com.xuyiming.cmfz.dao;

import com.xuyiming.cmfz.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 阿斯加的酱油 on 2018/10/10.
 */
public interface LogDao {

    public void insertLog(Log log);

    public List<Log> selectLog(@Param("begin")Integer nowPage,@Param("size")Integer pageSize);

    public Integer selectCount();
}
