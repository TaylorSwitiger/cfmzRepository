package com.xuyiming.cmfz.service.impl;

import com.xuyiming.cmfz.dao.ManagerDao;
import com.xuyiming.cmfz.dao.MasterDao;
import com.xuyiming.cmfz.entity.Manager;
import com.xuyiming.cmfz.entity.Master;
import com.xuyiming.cmfz.service.MasterService;
import com.xuyiming.cmfz.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 阿斯加的酱油 on 2018/10/8.
 */
@Service
@Transactional
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterDao masterDao;
    @Autowired
    private ManagerDao managerDao;

    public List<Master> queryMaster() {
        List<Master> masters = new ArrayList<Master>();

        masters = masterDao.selectMaster();

        return masters;
    }

    public Map<String,Object> queryMaster(Integer nowPage, Integer pageSize) {

        Map<String,Object> map = new HashMap<String, Object>();

        Integer count = masterDao.selectMasterCout();

        List<Master> pics = masterDao.selectMasterByPage((nowPage-1)*pageSize,pageSize);

        map.put("rows",pics);
        map.put("total",count);

        return map;
    }
    @Override
    public Boolean modifyMaster(Master master) {
        boolean flag = false;

        if (masterDao.updateMaster(master) > 0) {
            flag = true;
        }

        return flag;
    }

    public Map<String, Object> queryMaster(Integer nowPage, Integer pageSize, String key, String categroy) {
        Map<String,Object> map = new HashMap<String, Object>();

        Integer count = masterDao.selectMasterCoutByKey(key,categroy);

        List<Master> pics = masterDao.selectMasterByKey((nowPage-1)*pageSize,pageSize,key,categroy);

        map.put("rows",pics);
        map.put("total",count);

        return map;
    }

    @Transactional
    public Boolean addMaster(Master master) throws Exception {
        boolean flag = false;
        try {
            if (masterDao.insertMaster(master) > 0) {
                //添加社员时默认注册昵称为姓名密码为123456的登录账号
                Manager manager = new Manager();
                manager.setMgrId(UUID.randomUUID().toString().replace("-",""));
                manager.setMgrName(master.getMasterName());
                manager.setSalt(EncryptionUtil.getRandomSalt());
                manager.setMgrPwd(EncryptionUtil.getAlforithmPassword("123456", manager.getSalt()));
                managerDao.insertManager(manager);
                managerDao.insertManagerRole(manager.getMgrId());
                flag = true;
            }
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("账户已存在");
        }
        return flag;
    }

    public Boolean addMasterBatch(List<Master> master) {
        boolean flag = false;

        if (masterDao.insertMasterBatch(master) > 0) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    @Override
    public Boolean removeMaster(String masterId) {
        if (masterDao.deleteMaster(masterId) > 0) {
            if (managerDao.isExist(masterId) > 0) {
                return managerDao.deleteManager(masterId) > 0;
            } else {
                return true;
            }
        }
        return false;
    }
}
