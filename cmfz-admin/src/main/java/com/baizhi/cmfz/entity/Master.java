package com.baizhi.cmfz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Master {

    @Excel(name="编号")
    private Integer masterId;
    @Excel(name="法名")
    private String masterName;
    @Excel(name="电话")
    private String masterPhoto;
    @Excel(name="概述")
    private String masterrSummary;

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName == null ? null : masterName.trim();
    }

    public String getMasterPhoto() {
        return masterPhoto;
    }

    public void setMasterPhoto(String masterPhoto) {
        this.masterPhoto = masterPhoto == null ? null : masterPhoto.trim();
    }

    public String getMasterrSummary() {
        return masterrSummary;
    }

    public void setMasterrSummary(String masterrSummary) {
        this.masterrSummary = masterrSummary == null ? null : masterrSummary.trim();
    }

    @Override
    public String toString() {
        return "Master{" +
                "masterId=" + masterId +
                ", masterName='" + masterName + '\'' +
                ", masterPhoto='" + masterPhoto + '\'' +
                ", masterrSummary='" + masterrSummary + '\'' +
                '}';
    }
}