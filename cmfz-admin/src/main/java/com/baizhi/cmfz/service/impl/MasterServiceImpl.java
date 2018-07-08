package com.baizhi.cmfz.service.impl;

import com.baizhi.cmfz.dao.MasterDao;
import com.baizhi.cmfz.dao.PictureDao;
import com.baizhi.cmfz.entity.Master;
import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.MasterService;
import com.baizhi.cmfz.service.PictureService;
import com.baizhi.cmfz.util.DateConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 阿斯加的酱油 on 2018/7/8.
 */
@Service
@Transactional
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterDao masterDao;

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

    public Boolean addMaster(Master master) {
        boolean flag = false;

        if (masterDao.insertMaster(master) > 0) {
            flag = true;
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
}
