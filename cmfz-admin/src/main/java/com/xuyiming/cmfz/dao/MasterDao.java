package com.xuyiming.cmfz.dao;

import com.xuyiming.cmfz.entity.Master;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 阿斯加的酱油 on 2018/10/5.
 */
public interface MasterDao {
    /**
     * @Description 社员数量查询
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param       null
     * @Exception   null
     */
    public Integer selectMasterCout();

    /**
     * @Description 关键字社员数量查询
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param       null
     * @Exception   null
     */
    public Integer selectMasterCoutByKey(@Param("key")String key,@Param("category") String category);

    /**
     * @Description 社员分页查询
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param       begin:开始条数  ，size：每页条数
     * @Exception   null
     */
    public List<Master> selectMasterByPage(@Param("begin") Integer begin, @Param("size") Integer size);

    /**
     * @Description 社员关键字查询
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param       begin:开始条数  ，size：每页条数
     * @Exception   null
     */
    public List<Master> selectMasterByKey(@Param("begin") Integer begin, @Param("size") Integer size,@Param("key")String key,@Param("category")String category);

    /**
     * @Description 社员更新
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param
     * @Exception   null
     */
    public int updateMaster(Master master);

    /**
     * @Description 社员插入
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param
     * @Exception   null
     */
    public int insertMaster(Master master);

    /**
     * @Description 社员批量插入
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param
     * @Exception   null
     */
    public int insertMasterBatch(@Param("masters") List<Master> masters);

    /**
     * @Description 所有社员查询
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param
     * @Exception   null
     */
    public List<Master> selectMaster();

    public int deleteMaster(@Param("masterId") String masterId);

}
