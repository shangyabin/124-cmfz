package com.baizhi.service;

import org.springframework.boot.Banner;

import java.util.List;

/**
 * Created by 小尚 on 2018/8/29.
 */
public interface BannerService {
    //遍历所有
    public List<Banner> queryAll();
}
