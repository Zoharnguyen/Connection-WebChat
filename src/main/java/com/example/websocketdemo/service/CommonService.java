package com.example.websocketdemo.service;

import com.example.websocketdemo.dto.UserDesireDto;
import com.example.websocketdemo.dto.UserDto;
import com.example.websocketdemo.dto.UserProfileDto;
import com.example.websocketdemo.entity.UserProfile;

import java.util.List;

public interface CommonService {

    public void signUp(UserDto userDto);
    public void editProfile(UserDto userDto);
    public List<UserProfileDto> sortUserProfileByObject(List<UserProfile> userProfileList, UserDesireDto userDesireDto);

}
