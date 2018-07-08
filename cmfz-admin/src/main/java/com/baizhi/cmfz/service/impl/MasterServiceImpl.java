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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/7/8.
 */
@Service
@Transactional
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterDao masterDao;

    public Map<String,Object> queryMaster(Integer nowPage, Integer pageSize) {

        Map<String,Object> map = new HashMap<String, Object>();

        Integer count = masterDao.selectMasterCout();

        List<Picture> pics = masterDao.selectMasterByPage((nowPage-1)*pageSize,pageSize);

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

    public Boolean addMaster(Master master) {
        boolean flag = false;

        if (masterDao.insertMaster(master) > 0) {
            flag = true;
        }

        return flag;
    }
}
