package com.baizhi.service.impl;


import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 小尚 on 2018/8/29.
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public List<Banner> queryAll() {

        return bannerDao.queryAll();
    }

    @Override
    public void insertBanner(Banner banner) {
        bannerDao.insertBanner(banner);
    }

    @Override
    public void updateBanner(String status,int id) {
        bannerDao.updateBanner(status,id);
    }

    @Override
    public void deleteBanner(int id) {
        bannerDao.deleteBanner(id);
    }


}
