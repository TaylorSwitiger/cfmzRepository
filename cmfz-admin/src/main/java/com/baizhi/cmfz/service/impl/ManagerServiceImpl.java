package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.ManagerDao;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.entity.Picture;
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

    public Map<String,Object> queryPicture(Integer nowPage, Integer pageSize) {

        Map<String,Object> map = new HashMap<String, Object>();

        Integer count = managerDao.selectPictureCout();

        List<Picture> pics = managerDao.selectPictureByPage((nowPage-1)*pageSize,pageSize);

        map.put("rows",pics);
        map.put("total",count);

        return map;
    }

    public Boolean modifyPicture(Picture picture) {
        boolean flag = false;

        picture.setPictureDate(DateConvertUtil.toUtilDate(DateConvertUtil.toString(new Date())));

        if (managerDao.updatePicture(picture) > 0) {
            flag = true;
        }

        return flag;
    }

    public Boolean addPicture(Picture picture) {
        boolean flag = false;

        picture.setPictureDate(DateConvertUtil.toSqlDate(picture.getPictureDate()));

        if (managerDao.insertPicture(picture) > 0) {
            flag = true;
        }

        return flag;
    }
}
