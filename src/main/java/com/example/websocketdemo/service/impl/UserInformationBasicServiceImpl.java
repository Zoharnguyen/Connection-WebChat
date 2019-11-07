package com.example.websocketdemo.service.impl;

import com.example.websocketdemo.entity.UserInformationBasic;
import com.example.websocketdemo.repos.UserInformationBasicRepos;
import com.example.websocketdemo.service.UserInformationBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationBasicServiceImpl implements UserInformationBasicService {

    @Autowired
    UserInformationBasicRepos userInformationBasicRepos;

    @Override
    public UserInformationBasic save(UserInformationBasic userInformationBasic) {
        return userInformationBasicRepos.save(userInformationBasic);
    }

}
