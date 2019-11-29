package com.example.websocketdemo.service.impl;

import com.example.websocketdemo.entity.UserProfile;
import com.example.websocketdemo.repos.UserProfileRepos;
import com.example.websocketdemo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepos userProfileRepos;

    @Override
    public UserProfile save(UserProfile userProfile) {
        return userProfileRepos.save(userProfile);
    }

    @Override
    public UserProfile findByIdUser(UUID id) {
        return userProfileRepos.findByIdUser(id);
    }

    @Override
    public List<UserProfile> getAllUserProfile() {
        Iterable<UserProfile> userProfileIterable = userProfileRepos.findAll();
        List<UserProfile> userProfileList = new ArrayList<UserProfile>();
        userProfileIterable.forEach(userProfileList::add);
        return userProfileList;
    }

}
