package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 小尚 on 2018/9/4.
 */
public interface UserDao {
    //查询所有
    public List<User> export();
    //添加
    public void insert(User user);
    //修改
    public void updateUser(@Param("status") int status,@Param("id") int id);

}
