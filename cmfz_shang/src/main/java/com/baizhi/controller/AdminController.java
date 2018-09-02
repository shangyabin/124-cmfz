package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by 小尚 on 2018/8/29.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    //登陆
    @RequestMapping("/loginin")
    public String loginin(HttpSession session,Admin admin){
        //判断对象是否为空
        //为空重新登陆
        if(admin==null){
            return "login";
        }
        //调用方法
        boolean login = adminService.login(admin.getUsername(), admin.getPassword());
        //不为空
        if(login){
            Admin admin1 = adminService.getByUsername(admin.getUsername());
            session.setAttribute("admin1",admin1);
            return "/main/main";
        }
        return "login";
    }
    //退出
    @RequestMapping("/exit")
    public String  exit(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }
}
