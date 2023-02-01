package com.service;

import com.model.dto.UserDto;
import com.model.entity.User;
public class DtoMapper {
    private DtoMapper(){}
    public static UserDto toDto(User user) {
        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getDateOfBirthday()
        );
    }
}
