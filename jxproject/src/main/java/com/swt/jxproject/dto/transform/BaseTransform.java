package com.swt.jxproject.dto.transform;

import com.swt.jxproject.dto.UserDto;
import com.swt.jxproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


public interface BaseTransform<D,E> {
    D toDto(E e);
    E toEntity(D d);
}
