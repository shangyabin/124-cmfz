package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

/**
 * Created by 小尚 on 2018/8/30.
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //遍历所有
    @RequestMapping("/getAllAlbum")
    public @ResponseBody
    Map getAllAlbum() {
        //创建一个Map
        Map map = new HashMap();
        //遍历所有行数下的
        map.put("rows", albumService.getAlbum());
        return map;
    }

    @RequestMapping("/queryByAlbum")
    public Album queryByAlbum(int id) {
        return albumService.getByAlbumId(id);
    }

    //添加专辑
    @RequestMapping("/insertAlbum")
    public @ResponseBody
    void insertAlbum(Album album, HttpSession session, MultipartFile img) {
        System.out.println(album+"---------------------专辑collection");
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
        String uploadFilePath = realPath + "upload/album";
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
        album.setCoverImg(newName);
        System.out.println(album+"-------------------------");
        albumService.insertAlbum(album);
    }

    //删除专辑
    @RequestMapping("/deleteAlbum")
    public @ResponseBody
    void deleteAlbum(int id) {
        albumService.deleteAlbum(id);
    }

}
