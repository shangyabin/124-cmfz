package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.entity.UserDto;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    //用户分布地
    @ResponseBody
    @RequestMapping("/map")
    public Map<String,Object> map(){
        Map<String,Object> map = new HashMap<>();
        List<Object> mans = new ArrayList<>();
        mans.add(new UserDto("河南",30000));
        mans.add(new UserDto("河北",100));
        mans.add(new UserDto("山东",2456));
        mans.add(new UserDto("山西",243));
        mans.add(new UserDto("台湾",3055));
        mans.add(new UserDto("广西",303));
        mans.add(new UserDto("云南",304));

        List<Object> womens = new ArrayList<>();
        womens.add(new UserDto("北京",3042));
        womens.add(new UserDto("河南",303432));
        womens.add(new UserDto("广西",3045));
        womens.add(new UserDto("上海",30543));
        womens.add(new UserDto("成都",3012));
        womens.add(new UserDto("广东",301));

        map.put("man",mans);
        map.put("womens",womens);
        return map;
    }
}
