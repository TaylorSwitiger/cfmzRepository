package com.xuyiming.cmfz.service;

import com.xuyiming.cmfz.entity.Picture;

import java.util.List;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/10/8.
 */
public interface PictureService {

    public Map<String,Object> queryPicture(Integer nowPage, Integer pageSize);

    public Boolean modifyPicture(Picture picture);

    public Boolean addPicture(Picture picture);

    public List<String> queryPicturePath();

}
