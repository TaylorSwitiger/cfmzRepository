package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Master;
import com.baizhi.cmfz.entity.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 阿斯加的酱油 on 2018/7/8.
 */
public interface MasterDao {
    /**
     * @Description 上师数量查询
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param       null
     * @Exception   null
     */
    public Integer selectMasterCout();

    /**
     * @Description 关键字上师数量查询
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param       null
     * @Exception   null
     */
    public Integer selectMasterCoutByKey(@Param("key")String key,@Param("category") String category);

    /**
     * @Description 上师分页查询
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param       begin:开始条数  ，size：每页条数
     * @Exception   null
     */
    public List<Master> selectMasterByPage(@Param("begin") Integer begin, @Param("size") Integer size);

    /**
     * @Description 上师关键字查询
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param       begin:开始条数  ，size：每页条数
     * @Exception   null
     */
    public List<Master> selectMasterByKey(@Param("begin") Integer begin, @Param("size") Integer size,@Param("key")String key,@Param("category")String category);

    /**
     * @Description 上师更新
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param
     * @Exception   null
     */
    public int updateMaster(Master master);

    /**
     * @Description 上师插入
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param
     * @Exception   null
     */
    public int insertMaster(Master master);

    /**
     * @Description 上师批量插入
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param
     * @Exception   null
     */
    public int insertMasterBatch(@Param("masters") List<Master> masters);

    /**
     * @Description 所有上师查询
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param
     * @Exception   null
     */
    public List<Master> selectMaster();

}
