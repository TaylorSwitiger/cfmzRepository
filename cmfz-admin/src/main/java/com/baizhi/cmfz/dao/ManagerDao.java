package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Manager;

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

}
