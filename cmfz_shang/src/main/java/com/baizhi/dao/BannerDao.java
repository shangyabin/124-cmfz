package com.baizhi.dao;

import org.springframework.boot.Banner;

import java.util.List;

/**
 * Created by 小尚 on 2018/8/29.
 */
public interface BannerDao {
    //查所有
    public List<Banner> queryAll();

}
