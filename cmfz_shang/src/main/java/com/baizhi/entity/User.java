package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;


import java.util.Date;

/**
 * Created by 小尚 on 2018/9/4.
 */
public class User {
    private int id;  //id
    private String name;  //名字
    private String dharmaName;  //法名
    private String password;  //密码
    private int sex; //性别
    private String province;  //省份
    private String city;  //城市
    private String sign;  //描述
    private String phoneNum;  //电话号
    private String salt;  //盐值
    private int status;  //状态
    private String photoImg;//图片
    @JSONField(format = "yyyy-MM-dd")
    private Date registDate;  //创建日期

    public User() {
    }

    public User(int id, String name, String dharmaName, String password, int sex, String province, String city, String sign, String phoneNum, String salt, int status, String photoImg, Date registDate) {
        this.id = id;
        this.name = name;
        this.dharmaName = dharmaName;
        this.password = password;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.sign = sign;
        this.phoneNum = phoneNum;
        this.salt = salt;
        this.status = status;
        this.photoImg = photoImg;
        this.registDate = registDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", salt='" + salt + '\'' +
                ", status=" + status +
                ", photoImg='" + photoImg + '\'' +
                ", registDate=" + registDate +
                '}';
    }
}
