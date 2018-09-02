package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 小尚 on 2018/9/1.
 */
public class Chapter implements Serializable{
    private int id;
    private String title;
    private String duration;
    private String size;
    private Date createDate;
    private String audioPath;
    private int albumId;

    public Chapter() {
    }

    public Chapter(int id, String title, String duration, String size, Date createDate, String audioPath, int albumId) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.size = size;
        this.createDate = createDate;
        this.audioPath = audioPath;
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", size='" + size + '\'' +
                ", createDate=" + createDate +
                ", audioPath='" + audioPath + '\'' +
                ", albumId=" + albumId +
                '}';
    }
}
