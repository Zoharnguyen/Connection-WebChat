package com.example.websocketdemo.service;

import com.example.websocketdemo.dto.MessageDto;
import com.example.websocketdemo.dto.UserDesireDto;
import com.example.websocketdemo.dto.UserDto;
import com.example.websocketdemo.dto.UserProfileDto;
import com.example.websocketdemo.entity.Message;
import com.example.websocketdemo.entity.MessageList;
import com.example.websocketdemo.entity.UserProfile;

import java.util.List;

public interface CommonService {

    public void signUp(UserDto userDto);
    public void editProfile(UserDto userDto);
    public List<UserProfileDto> sortUserProfileByObject(List<UserProfile> userProfileList, UserDesireDto userDesireDto);
    public boolean compareString(String str1, String str2);
    public List<MessageList> executeMessageLists(List<MessageList> messageLists);
    public List<MessageDto> sortMessageByTime(List<Message> messageList01, List<Message> messageList02);
    public List<MessageDto> convertMessageListToMessageListDto(List<Message> messageList);

}
