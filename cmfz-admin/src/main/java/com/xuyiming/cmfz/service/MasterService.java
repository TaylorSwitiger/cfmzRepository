package com.xuyiming.cmfz.service;

import com.xuyiming.cmfz.entity.Master;

import java.util.List;
import java.util.Map;

/**
 * Created by 阿斯加的酱油 on 2018/10/8.
 */
public interface MasterService {
    public List<Master> queryMaster();

    public Map<String,Object> queryMaster(Integer nowPage, Integer pageSize);

    public Map<String,Object> queryMaster(Integer nowPage, Integer pageSize,String key,String categroy);

    public Boolean modifyMaster(Master master);

    public Boolean addMaster(Master master) throws Exception;

    public Boolean addMasterBatch(List<Master> master);

    public Boolean removeMaster(String masterId);
}
