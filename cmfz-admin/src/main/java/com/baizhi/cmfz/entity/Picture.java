package com.baizhi.cmfz.entity;

import java.util.Date;

public class Picture {
    private Integer pictureId;

    private String picturePath;

    private Date pictureDate;

    private String pictureDescription;

    private String pictureStatus;

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }

    public Date getPictureDate() {
        return pictureDate;
    }

    public void setPictureDate(Date pictureDate) {
        this.pictureDate = pictureDate;
    }

    public String getPictureDescription() {
        return pictureDescription;
    }

    public void setPictureDescription(String pictureDescription) {
        this.pictureDescription = pictureDescription == null ? null : pictureDescription.trim();
    }

    public String getPictureStatus() {
        return pictureStatus;
    }

    public void setPictureStatus(String pictureStatus) {
        this.pictureStatus = pictureStatus == null ? null : pictureStatus.trim();
    }
}