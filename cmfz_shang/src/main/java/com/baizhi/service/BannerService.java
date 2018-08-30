package com.baizhi.service;



import com.baizhi.entity.Banner;

import java.util.List;

/**
 * Created by 小尚 on 2018/8/29.
 */
public interface BannerService {
    //遍历所有
    public List<Banner> queryAll();
    //添加
    public void insertBanner(Banner banner);
    //修改
    public void updateBanner(String status,int id);
    //删除
    public void deleteBanner(int id);
}
