package com.swt.jxproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.swt.jxproject.mapper")
@SpringBootApplication
public class JxprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JxprojectApplication.class, args);
    }

}
