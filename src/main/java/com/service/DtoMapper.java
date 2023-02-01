package com.service;

import com.model.dto.UserDto;
import com.model.entity.User;

import java.time.LocalDate;
import java.time.Period;

public class DtoMapper {
    private DtoMapper() {
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getName(),
                user.getSurname(),
                Period.between(user.getDateOfBirthday(), LocalDate.now())
                        .getYears()
        );
    }
}
