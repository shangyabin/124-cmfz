package com.baizhi.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 小尚 on 2018/8/29.
 */
public class banner implements Serializable{
    private int id;
    private String title;  //标题
    private String url;  //图片路径
    private String description;  //图片描述
    private String status;  //状态

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;  //上传日期

    public banner(int id, String title, String url, String description, String status, Date createDate) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
    }

    public banner() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "banner{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
