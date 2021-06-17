package com.swt.jxproject.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swt.jxproject.dto.UserDto;
import com.swt.jxproject.dto.transform.UserTransform;
import com.swt.jxproject.entity.User;
import com.swt.jxproject.mapper.UserMapper;
import com.swt.jxproject.services.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserTransform userTransform;

    @Override
    public UserDto findUserByNameEq(String name) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",name);
        User user = baseMapper.selectOne(queryWrapper);
        UserDto userDto = userTransform.toDto(user);
        return userDto;
    }

    public UserDto findUserById(Long id) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user = baseMapper.selectOne(queryWrapper);
        UserDto userDto = userTransform.toDto(user);
        return userDto;
    }
}
