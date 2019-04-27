package com.xuyiming.cmfz.dao;

import com.xuyiming.cmfz.entity.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 阿斯加的酱油 on 2018/10/8.
 */
public interface PictureDao {
    /**
     * @Description 海报数量查询
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param       null
     * @Exception   null
     */
    public Integer selectPictureCout();

    /**
     * @Description 海报分页查询
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param       begin:开始条数  ，size：每页条数
     * @Exception   null
     */
    public List<Picture> selectPictureByPage(@Param("begin") Integer begin, @Param("size") Integer size);

    public List<String> selectPicturePathByStatus();

    /**
     * @Description 海报更新
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param       picture：海报信息
     * @Exception   null
     */
    public int updatePicture(Picture picture);

    /**
     * @Description 海报插入
     * @Author      徐一鸣
     * @Time        2018-10-05 23:13:30
     * @Param       picture：海报信息
     * @Exception   null
     */
    public int insertPicture(Picture picture);
}
