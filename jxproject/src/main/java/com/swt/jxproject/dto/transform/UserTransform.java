package com.swt.jxproject.dto.transform;

import com.swt.jxproject.dto.UserDto;
import com.swt.jxproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserTransform extends BaseTransform<UserDto,User> {
    @Override
    @Mapping(target = "password",ignore = true)
    UserDto toDto(User user);
}
