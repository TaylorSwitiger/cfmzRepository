package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.entity.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 管理员数据访问层接口
 * @Author 徐一鸣
 * @Time 2018-07-04 15:03:30
 */
public interface ManagerDao {

    /**
     * @Description 管理员登录时查找用户抽象方法
     * @Author      徐一鸣
     * @Time        2018-07-04 15:05:30
     * @Param       mgrName 管理员姓名
     * @Exception   null
     */
    public Manager selectManager(String mgrName);

    /**
     * @Description 侧面导航菜单查询
     * @Author      徐一鸣
     * @Time        2018-07-05 12:13:30
     * @Param       null
     * @Exception   null
     */
    public List<Menu> selectMenu();

    /**
     * @Description 轮播图数量查询
     * @Author      徐一鸣
     * @Time        2018-07-05 23:13:30
     * @Param       null
     * @Exception   null
     */
    public Integer selectPictureCout();

    public List<Picture> selectPictureByPage(@Param("begin") Integer begin,@Param("size") Integer size);
}
