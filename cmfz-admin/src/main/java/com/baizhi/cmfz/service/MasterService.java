package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Master;
import com.baizhi.cmfz.entity.Picture;

import java.util.List;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/7/8.
 */
public interface MasterService {
    public List<Master> queryMaster();

    public Map<String,Object> queryMaster(Integer nowPage, Integer pageSize);

    public Map<String,Object> queryMaster(Integer nowPage, Integer pageSize,String key,String categroy);

    public Boolean modifyMaster(Master master);

    public Boolean addMaster(Master master);

    public Boolean addMasterBatch(List<Master> master);
}
