package com.swt.jxproject.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swt.jxproject.dto.UserDto;
import com.swt.jxproject.entity.User;
import org.mapstruct.Mapping;

public interface UserService extends IService<User> {
    UserDto findUserByNameEq(String name);

    public UserDto findUserById(Long id);
}
