package com.xuyiming.cmfz.service.impl;

import com.xuyiming.cmfz.dao.ManagerDao;
import com.xuyiming.cmfz.entity.*;
import com.xuyiming.cmfz.service.ManagerService;
import com.xuyiming.cmfz.util.EncryptionUtil;
import com.xuyiming.cmfz.entity.Manager;
import com.xuyiming.cmfz.entity.Menu;
import com.xuyiming.cmfz.entity.SysPermission;
import com.xuyiming.cmfz.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Description 管理员服务层
 * @Author 徐一鸣
 * @Time 2018-10-04 15:10:30
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

   @Autowired
    private ManagerDao managerDao;

    /**
     * @Description 管理员登录时查找用户方法
     * @Author      徐一鸣
     * @Time        2018-07-04 15:12:30
     * @Param       mgrName 管理员姓名
     * @Exception   null
     */
    public Manager queryManager(String mgrName, String mgrPwd) {
        Manager mgr = null;

        mgr = managerDao.selectManager(mgrName);

        if (mgr != null) {
            String password = EncryptionUtil.encryptionCode(mgrPwd + mgr.getSalt());
            if (!mgr.getMgrPwd().equals(password)) {
                mgr = null;
            };
        }
        return mgr;
    }

    public List<Menu> queryMenu(){

        List<Menu> menus = new ArrayList<Menu>();

        menus = managerDao.selectMenu();

        return menus;
    }

    public List<SysRole> queryRoleByManagerName(String managerName) {
        return managerDao.selectRoleByManagerName(managerName);
    }

    public List<SysPermission> queryPermissionByManagerName(String managerName) {
        return managerDao.selectPermissionByManagerName(managerName);
    }

    @Override
    public boolean changeManager(Manager manager) {
        if (managerDao.updateManager(manager) > 0 ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addManager(Manager manager) {
        if (managerDao.insertManager(manager) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String,Object> queryManagerByPage(Integer nowPage, Integer pageSize) {
        Map<String,Object> map = new HashMap<String, Object>();

        Integer count = managerDao.selectManagerCout();

        List<Manager> pics = managerDao.selectManagerByPage((nowPage-1)*pageSize,pageSize);

        map.put("rows",pics);
        map.put("total",count);

        return map;
    }

    @Override
    public Map<String, Object> queryManagerByKey(Integer nowPage, Integer pageSize, String key, String categroy) {
        Map<String,Object> map = new HashMap<String, Object>();

        Integer count = managerDao.selectManagerCoutByKey(key,categroy);

        List<Manager> pics = managerDao.selectManagerByKey((nowPage-1)*pageSize,pageSize,key,categroy);

        map.put("rows",pics);
        map.put("total",count);

        return map;
    }

    @Override
    public boolean changeManagerRole(Integer roleId, String managerId) {
        if (managerDao.updateManagerRole(roleId, managerId) > 0) {
            return true;
        }
        return false;
    }
}
