package com.example.websocketdemo.service;

import com.example.websocketdemo.entity.UserProfile;

import java.util.List;
import java.util.UUID;

public interface UserProfileService {

    UserProfile save(UserProfile userProfile);
    UserProfile findByIdUser(UUID id);
    List<UserProfile> getAllUserProfile();

}
