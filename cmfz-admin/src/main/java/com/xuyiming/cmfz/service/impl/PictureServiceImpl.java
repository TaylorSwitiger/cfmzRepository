package com.xuyiming.cmfz.service.impl;

import com.xuyiming.cmfz.dao.PictureDao;
import com.xuyiming.cmfz.entity.Picture;
import com.xuyiming.cmfz.service.PictureService;
import com.xuyiming.cmfz.util.DateConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/10/8.
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    public Map<String,Object> queryPicture(Integer nowPage, Integer pageSize) {

        Map<String,Object> map = new HashMap<String, Object>();

        Integer count = pictureDao.selectPictureCout();

        List<Picture> pics = pictureDao.selectPictureByPage((nowPage-1)*pageSize,pageSize);

        map.put("rows",pics);
        map.put("total",count);

        return map;
    }

    public List<String> queryPicturePath() {
        return pictureDao.selectPicturePathByStatus();
    }

    public Boolean modifyPicture(Picture picture) {
        boolean flag = false;

        picture.setPictureDate(DateConvertUtil.toUtilDate(DateConvertUtil.toString(new Date())));

        if (pictureDao.updatePicture(picture) > 0) {
            flag = true;
        }

        return flag;
    }

    public Boolean addPicture(Picture picture) {
        boolean flag = false;

        picture.setPictureDate(DateConvertUtil.toSqlDate(picture.getPictureDate()));
        //System.out.println(picture);
        if (pictureDao.insertPicture(picture) > 0) {
            flag = true;
        }

        return flag;
    }
}
