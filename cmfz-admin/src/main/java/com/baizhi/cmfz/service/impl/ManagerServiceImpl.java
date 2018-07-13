package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.ManagerDao;
import com.baizhi.cmfz.entity.*;
import com.baizhi.cmfz.service.ManagerService;
import com.baizhi.cmfz.util.DateConvertUtil;
import com.baizhi.cmfz.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Description 管理员服务层
 * @Author 徐一鸣
 * @Time 2018-07-04 15:10:30
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
    public Manager queryManager(String mgrName,String mgrPwd) {
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
}
