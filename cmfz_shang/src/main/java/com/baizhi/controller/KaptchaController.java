package com.baizhi.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by 小尚 on 2018/8/29.
 */
@Controller
public class KaptchaController {
    //通过google提供的验证码工具Kaptcha完成验证码的生成
    //验证码工具中的一个接口，在接口中定义的有方法，可以生成文本内容以及图片
    //面向接口编程
    @Autowired
    private Producer producer;

    //生成一个验证码
    @RequestMapping("/getKaptcha")
    public void getKaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        //图片中的文本内容
        String text = producer.createText();
        //把生成的文本内容,放置在session作用域中
        session.setAttribute("Katcha", text);
        //通过文本内容,创建一个图片===创建一个图片,把文本内容放到图片中
        BufferedImage image = producer.createImage(text);
        //以流的方式相应图片到jsp页面
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
