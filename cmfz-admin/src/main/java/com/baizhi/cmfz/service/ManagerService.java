package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.*;

import java.util.List;
import java.util.Map;

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

    public List<Menu> queryMenu();

    public List<SysRole> queryRoleByManagerName(String managerName);

    public List<SysPermission> queryPermissionByManagerName(String managerName);
}
