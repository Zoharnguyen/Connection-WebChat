package com.example.websocketdemo.service.impl;

import com.example.websocketdemo.dto.UserDto;
import com.example.websocketdemo.entity.UserInformationBasic;
import com.example.websocketdemo.service.CommonService;
import com.example.websocketdemo.service.UserInformationBasicService;
import com.example.websocketdemo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserInformationBasicService userInformationBasicService;

    public UserDto register(UserDto userDto) {
        UserInformationBasic userInformationBasic = new UserInformationBasic();
        return  userDto;
    }

}
