package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 小尚 on 2018/8/29.
 */

public class Menu implements Serializable{
    private int id;
    private String title;  //名称
    private String iconCls;  //图标
    private String href;  //路径
    private int parentId;  //父类id
    private List<Menu> secondMenu;  //集合

    public Menu() {
    }

    public Menu(int id, String title, String iconCls, String href, int parentId, List<Menu> secondMenu) {
        this.id = id;
        this.title = title;
        this.iconCls = iconCls;
        this.href = href;
        this.parentId = parentId;
        this.secondMenu = secondMenu;
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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getSecondMenu() {
        return secondMenu;
    }

    public void setSecondMenu(List<Menu> secondMenu) {
        this.secondMenu = secondMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", href='" + href + '\'' +
                ", parentId=" + parentId +
                ", secondMenu=" + secondMenu +
                '}';
    }
}
