package com.baizhi.cmfz.entity;

public class Master {
    private Integer masterId;

    private String masterName;

    private String masterPhoto;

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
}