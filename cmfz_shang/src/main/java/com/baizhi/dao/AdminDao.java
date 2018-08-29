package com.baizhi.dao;

import com.baizhi.entity.Admin;

/**
 * Created by 小尚 on 2018/8/29.
 */
public interface AdminDao {
    //查询用户名
    public Admin getByUsername(String username);
}
