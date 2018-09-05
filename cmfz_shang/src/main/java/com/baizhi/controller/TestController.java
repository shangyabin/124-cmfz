package com.baizhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小尚 on 2018/9/5.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @ResponseBody
    @RequestMapping("/test")
    public List<Integer> test(HttpSession session){
        List<Integer> integers=new ArrayList<>();
        integers.add(5);
        integers.add(18);
        integers.add(20);
        return integers;
    }
}
