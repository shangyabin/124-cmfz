package com.baizhi.service;

import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 小尚 on 2018/9/4.
 */
public interface UserService {
    //查询所有
    public List<User>  user();
	//导出
    public void export(HttpServletResponse response);
    //导入
    public void userImport(MultipartFile file);
    //部分导出
    public void customerExport(String title, String fileds, HttpServletResponse response);
    //更改状态
    public void update(int status,int id);
}
