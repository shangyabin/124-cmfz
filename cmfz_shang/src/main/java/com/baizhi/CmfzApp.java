package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by 小尚 on 2018/8/29.
 */
@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class CmfzApp {
    public static void main(String[] args) {
        SpringApplication.run(CmfzApp.class,args);
    }
}
