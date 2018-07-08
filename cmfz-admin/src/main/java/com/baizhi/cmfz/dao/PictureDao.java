package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 阿斯加的酱油 on 2018/7/8.
 */
public interface PictureDao {
    /**
     * @Description 轮播图数量查询
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param       null
     * @Exception   null
     */
    public Integer selectPictureCout();

    /**
     * @Description 轮播图分页查询
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param       begin:开始条数  ，size：每页条数
     * @Exception   null
     */
    public List<Picture> selectPictureByPage(@Param("begin") Integer begin, @Param("size") Integer size);

    /**
     * @Description 轮播图更新
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param       picture：轮播图信息
     * @Exception   null
     */
    public int updatePicture(Picture picture);

    /**
     * @Description 轮播图插入
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param       picture：轮播图信息
     * @Exception   null
     */
    public int insertPicture(Picture picture);
}
