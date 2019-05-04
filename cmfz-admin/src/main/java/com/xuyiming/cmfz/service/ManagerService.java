package com.xuyiming.cmfz.service;

import com.xuyiming.cmfz.entity.*;
import com.xuyiming.cmfz.entity.Manager;
import com.xuyiming.cmfz.entity.Menu;
import com.xuyiming.cmfz.entity.SysPermission;
import com.xuyiming.cmfz.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @Description 管理员服务层接口
 * @Author 徐一鸣
 * @Time 2018-10-04 15:10:30
 */
public interface ManagerService {

    /**
     * @Description 管理员登录时查找用户抽象方法
     * @Author      徐一鸣
     * @Time        2018-10-04 15:11:30
     * @Param       mgrName 管理员姓名
     * @Exception   null
     */
    public Manager queryManager(String mgrName, String mgrPwd);

    public List<Menu> queryMenu();

    public List<SysRole> queryRoleByManagerName(String managerName);

    public List<SysPermission> queryPermissionByManagerName(String managerName);

    public boolean changeManager(Manager manager);

    public boolean addManager(Manager manager);

    public Map<String,Object> queryManagerByPage(Integer nowPage, Integer pageSize);

    public Map<String,Object> queryManagerByKey(Integer nowPage, Integer pageSize,String key,String categroy);

    public boolean changeManagerRole(Integer roleId, String managerId);

    public boolean removeManagerRole(String managerId);

    public boolean removeManager(String mgrId);
}
