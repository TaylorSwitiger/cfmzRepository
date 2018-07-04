package com.baizhi.cmfz.entity;

public class Audio {
    private Integer audioId;

    private String audioName;

    private String audioSinger;

    private String audioCompose;

    private String audioArrangement;

    private String audioProducer;

    private String audioSize;

    private String audioAdd;

    private String albumId;

    private Integer audioCount;

    public Integer getAudioId() {
        return audioId;
    }

    public void setAudioId(Integer audioId) {
        this.audioId = audioId;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName == null ? null : audioName.trim();
    }

    public String getAudioSinger() {
        return audioSinger;
    }

    public void setAudioSinger(String audioSinger) {
        this.audioSinger = audioSinger == null ? null : audioSinger.trim();
    }

    public String getAudioCompose() {
        return audioCompose;
    }

    public void setAudioCompose(String audioCompose) {
        this.audioCompose = audioCompose == null ? null : audioCompose.trim();
    }

    public String getAudioArrangement() {
        return audioArrangement;
    }

    public void setAudioArrangement(String audioArrangement) {
        this.audioArrangement = audioArrangement == null ? null : audioArrangement.trim();
    }

    public String getAudioProducer() {
        return audioProducer;
    }

    public void setAudioProducer(String audioProducer) {
        this.audioProducer = audioProducer == null ? null : audioProducer.trim();
    }

    public String getAudioSize() {
        return audioSize;
    }

    public void setAudioSize(String audioSize) {
        this.audioSize = audioSize == null ? null : audioSize.trim();
    }

    public String getAudioAdd() {
        return audioAdd;
    }

    public void setAudioAdd(String audioAdd) {
        this.audioAdd = audioAdd == null ? null : audioAdd.trim();
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId == null ? null : albumId.trim();
    }

    public Integer getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(Integer audioCount) {
        this.audioCount = audioCount;
    }
}