package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


import java.util.List;
import java.util.UUID;

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
    public @ResponseBody
    List<Banner> showAll() {
        List<Banner> banners = bannerService.queryAll();
        return banners;
    }

    @RequestMapping("/add")
    public @ResponseBody void add(MultipartFile img, HttpSession session,Banner banner){
        /*
        * 1.指定上传位置
        * 2.防止文件重名
        * 3.上传文件到指定文件夹
        *
        *   webapps   暂时 存放到项目中   分布式的文件存储系统
        *
        * */
//        项目的绝对路径 D:\source\final-project\cmfz\src\main\webapp\
        String realPath = session.getServletContext().getRealPath("/");
        String uploadFilePath = realPath + "upload/banner";
        File file = new File(uploadFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
//            1.jpg
        String originalFilename = img.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();
//              jpg
        String extension = FilenameUtils.getExtension(originalFilename);
//        newName
        String newName = uuid + "." + extension;
        try {
            img.transferTo(new File(uploadFilePath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4.添加数据库
        banner.setUrl(newName);
        bannerService.insertBanner(banner);
    }
    @RequestMapping("/update")
    public @ResponseBody void update(String status,int id){
        bannerService.updateBanner(status,id);
    }

    @RequestMapping("/delete")
    public @ResponseBody void delete(int id){
        bannerService.deleteBanner(id);
    }
}
