package com.swt.jxproject.controller;

import com.swt.jxproject.annotation.NoCheck;
import com.swt.jxproject.dto.UserDto;
import com.swt.jxproject.services.UserService;
import com.swt.jxproject.utils.ApplicationContextUtil;
import com.swt.jxproject.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("eqname")
    public UserDto eqName(String name){
        UserDto userByNameEq = userService.findUserByNameEq(name);
        return userByNameEq;
    }


    @GetMapping("hello")
    public String hello(String name){
        UserDto userByNameEq = userService.findUserByNameEq(name);
        System.out.println(userByNameEq);
        System.out.println(redisTemplate);
        return "hello";
    }

    @NoCheck
    @PostMapping("login")
    public String hello(@Valid @RequestBody UserDto userDto) throws Exception {
        JWTUtils jWTUtils = ApplicationContextUtil.getbean("jwtutils", JWTUtils.class);
        String token = jWTUtils.createToken(userDto.getId().toString(),userDto.getUsername());
        return token;
    }
}
