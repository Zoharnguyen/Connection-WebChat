package com.example.websocketdemo.service.impl;

import com.example.websocketdemo.entity.UserProfile;
import com.example.websocketdemo.repos.UserProfileRepos;
import com.example.websocketdemo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepos userProfileRepos;

    @Override
    public UserProfile save(UserProfile userProfile) {
        return userProfileRepos.save(userProfile);
    }

}
