package com.example.websocketdemo.service;

import com.example.websocketdemo.entity.UserInformationBasic;

import java.util.UUID;

public interface UserInformationBasicService {

    UserInformationBasic save(UserInformationBasic userInformationBasic);
    UserInformationBasic findByUserName(String userName);
    UserInformationBasic findById(UUID id);

}
