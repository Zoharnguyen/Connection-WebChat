package com.example.websocketdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserDto {

    String username;
    String password;
    String image;
    String fullName;
    String gender;
    LocalDate dateOfBirth;
    String sportFavourite;
    String filmCategoryFavourite;
    String cityAddress;

}
