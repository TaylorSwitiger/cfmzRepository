package com.xuyiming.cmfz.entity;

import java.util.Date;

/**
 * @Description 实体类
 * @Author 作者
 * @Time 2018-10-04 14:24:30
 */
public class Album {
    private String albumId;

    private String albumName;

    private String albumAuthor;

    private String albumCv;

    private Integer albumCount;

    private Integer albumScore;

    private String albumSrc;

    private Date publishDate;

    private String content;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId == null ? null : albumId.trim();
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName == null ? null : albumName.trim();
    }

    public String getAlbumAuthor() {
        return albumAuthor;
    }

    public void setAlbumAuthor(String albumAuthor) {
        this.albumAuthor = albumAuthor == null ? null : albumAuthor.trim();
    }

    public String getAlbumCv() {
        return albumCv;
    }

    public void setAlbumCv(String albumCv) {
        this.albumCv = albumCv == null ? null : albumCv.trim();
    }

    public Integer getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(Integer albumCount) {
        this.albumCount = albumCount;
    }

    public Integer getAlbumScore() {
        return albumScore;
    }

    public void setAlbumScore(Integer albumScore) {
        this.albumScore = albumScore;
    }

    public String getAlbumSrc() {
        return albumSrc;
    }

    public void setAlbumSrc(String albumSrc) {
        this.albumSrc = albumSrc == null ? null : albumSrc.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}