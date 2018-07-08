package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Picture;

import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/7/8.
 */
public interface PictureService {

    public Map<String,Object> queryPicture(Integer nowPage, Integer pageSize);

    public Boolean modifyPicture(Picture picture);

    public Boolean addPicture(Picture picture);

}
