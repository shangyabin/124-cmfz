package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 小尚 on 2018/8/29.
 */
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;
    //判断返回的对象和数据库中的是否相同
    @Override
    public boolean login(String username, String password) {
        //调用方法返回一个对象
        Admin admin = adminDao.getByUsername(username);
        //判断对象是否为空和密码相同
        if (admin!=null && admin.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    //返回一个对象
    @Override
    public Admin getByUsername(String username) {

        return adminDao.getByUsername(username);
    }
}
