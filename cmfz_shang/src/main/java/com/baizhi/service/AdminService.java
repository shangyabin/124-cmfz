package com.baizhi.service;

import com.baizhi.entity.Admin;

/**
 * Created by 小尚 on 2018/8/29.
 */
public interface AdminService {
    //登陆
    public boolean login(String username,String password);
    //根据用户名查询
    public Admin getByUsername(String username);
}
