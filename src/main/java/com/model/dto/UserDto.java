package com.model.dto;

import java.time.LocalDate;

public class UserDto {
    private String name;
    private String surname;
    private LocalDate dateOfBirthday;

    public UserDto(String name, String surname, LocalDate dateOfBirthday) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }
}
