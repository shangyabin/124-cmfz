package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 小尚 on 2018/8/30.
 */
public class Banner implements Serializable{
    private int id;
    private String title; //标题
    private String url;  //路径
    private String status;  //状态
    private Date createDate; //创建时间
    private String description; //描述

    public Banner() {
    }

    public Banner(int id, String title, String url, String status, Date createDate, String description) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.status = status;
        this.createDate = createDate;
        this.description = description;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", description='" + description + '\'' +
                '}';
    }
}
