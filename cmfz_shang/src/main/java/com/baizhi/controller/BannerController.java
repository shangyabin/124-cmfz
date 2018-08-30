package com.baizhi.controller;


import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 小尚 on 2018/8/29.
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    //遍历所有
    @RequestMapping("/showAll")
    public @ResponseBody List<Banner> showAll(){
        List<Banner> banners = bannerService.queryAll();
        return banners;
    }

}
