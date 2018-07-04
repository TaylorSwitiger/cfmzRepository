package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Manager;

/**
 * @Description 管理员服务层接口
 * @Author 徐一鸣
 * @Time 2018-07-04 15:10:30
 */
public interface ManagerService {

    /**
     * @Description 管理员登录时查找用户抽象方法
     * @Author      徐一鸣
     * @Time        2018-07-04 15:11:30
     * @Param       mgrName 管理员姓名
     * @Exception   null
     */
    public Manager queryManager(String mgrName,String mgrPwd);

}
