package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 小尚 on 2018/9/4.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //遍历所有
    @RequestMapping("/user")
    public @ResponseBody List<User> user(){
        return userService.user();

    }
    //导出
    @RequestMapping("/export")
    public void export(HttpServletResponse response){
        userService.export(response);
    }
    //导入
    @ResponseBody
    @RequestMapping("/userImport")
    public Map userImport(MultipartFile file){
        Map map=new HashMap();
        try {
            userService.userImport(file);
            map.put("isSuccess",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isSuccess",false);
        }
        System.out.println("------"+map);
        return map;
    }
    //自定义导出
    @RequestMapping("/customerExport")
    public void customerExport(String title,String fileds,HttpServletResponse response){
        userService.customerExport(title,fileds,response);
    }
    //更改状态
    @RequestMapping("/update")
    public @ResponseBody
    void update(int status, int id) {
        userService.update(status, id);
    }
}
