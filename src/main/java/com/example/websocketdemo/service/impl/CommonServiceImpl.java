package com.example.websocketdemo.service.impl;

import com.example.websocketdemo.dto.MessageDto;
import com.example.websocketdemo.dto.UserDesireDto;
import com.example.websocketdemo.dto.UserDto;
import com.example.websocketdemo.dto.UserProfileDto;
import com.example.websocketdemo.entity.Message;
import com.example.websocketdemo.entity.MessageList;
import com.example.websocketdemo.entity.UserInformationBasic;
import com.example.websocketdemo.entity.UserProfile;
import com.example.websocketdemo.service.CommonService;
import com.example.websocketdemo.service.UserInformationBasicService;
import com.example.websocketdemo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserInformationBasicService userInformationBasicService;

    @Override
    public void signUp(UserDto userDto) {
        UserInformationBasic userInformationBasic = new UserInformationBasic();
        userInformationBasic.setUserName(userDto.getUserName());
        userInformationBasic.setPassword(userDto.getPassword());
        UserProfile userProfile = new UserProfile();
        userProfile.setCityAddress(userDto.getCityAddress());
        userProfile.setGender(userDto.getGender());
        userProfile.setFilmCategoryFavourite(userDto.getFilmCategoryFavourite());
        userProfile.setUserInformationBasic(userInformationBasic);
        userProfile.setGameCategoryFavourite(userDto.getGameCategoryFavourite());
        userProfile.setMusicCategoryFavourite(userDto.getMusicCategoryFavourite());
        userProfile.setSportCategoryFavourite(userDto.getSportCategoryFavourite());
        userProfileService.save(userProfile);
    }

    @Override
    public void editProfile(UserDto userDto) {
        UserInformationBasic userInformationBasic = userInformationBasicService.findById(userDto.getIdUser());
        if(userInformationBasic != null){
            userInformationBasic.setUserName(userDto.getUserName());
            userInformationBasic.setPassword(userDto.getPassword());
            UserProfile userProfile = userProfileService.findByIdUser(userInformationBasic.getId());
            userProfile.setCityAddress(userDto.getCityAddress());
            userProfile.setGender(userDto.getGender());
            userProfile.setFilmCategoryFavourite(userDto.getFilmCategoryFavourite());
            userProfile.setUserInformationBasic(userInformationBasic);
            userProfile.setGameCategoryFavourite(userDto.getGameCategoryFavourite());
            userProfile.setMusicCategoryFavourite(userDto.getMusicCategoryFavourite());
            userProfile.setSportCategoryFavourite(userDto.getSportCategoryFavourite());
            userProfile.setAge(userDto.getAge());
            if(userDto.getImageProfile() != null) userProfile.setImageProfile(userDto.getImageProfile());
            if(userDto.getImageBackground() != null) userProfile.setImageBackground(userDto.getImageBackground());
            userProfileService.save(userProfile);
        }
    }

    @Override
    public List<UserProfileDto> sortUserProfileByObject(List<UserProfile> userProfileList, UserDesireDto userDesireDto) {
        List<UserProfileDto> userProfileDtoList = new ArrayList<>();
        userProfileDtoList = convertUserProfileListToUserProfileDtoList(userProfileList);
        userProfileDtoList = checkPointObjectList(userDesireDto,userProfileDtoList);
        userProfileDtoList = sortByPoint(userProfileDtoList);
        return userProfileDtoList;
    }

    @Override
    public boolean compareString(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                if(str1_ch > str2_ch) return true;
                else return false;
            }
        }
        if(l1 > l2) return true;
        return false;
    }

    @Override
    public List<MessageList> executeMessageLists(List<MessageList> messageLists) {

        List<MessageList> toRemoteList = new ArrayList<>();
        for(MessageList messageList : messageLists) {
            outLoop: for(MessageList messageList1 : messageLists) {
                messageList.setCreatedTime(findMaxTimestamp(messageList));
                messageList1.setCreatedTime(findMaxTimestamp(messageList1));
                if(messageList.getNameSender().equals(messageList1.getNameReceiver()) && messageList.getNameReceiver().equals(messageList1.getNameSender())) {
                    if(messageList.getCreatedTime().after(messageList1.getCreatedTime())) toRemoteList.add(messageList1);
                    else toRemoteList.add(messageList);
                    break outLoop;
                }
            }
        }
        messageLists.removeAll(toRemoteList);
        return messageLists;

    }

    @Override
    public List<MessageDto> sortMessageByTime(List<Message> messageList01, List<Message> messageList02) {
        messageList01.addAll(messageList02);
        List<MessageDto> messageDtos = convertMessageListToMessageListDto(messageList01);
        Collections.sort(messageDtos);
        return messageDtos;
    }

    @Override
    public List<MessageDto> convertMessageListToMessageListDto(List<Message> messageList) {

        List<MessageDto> messageDtos = new ArrayList<>();
        for(Message message : messageList) {
            MessageDto messageDto = new MessageDto();
            messageDto.setContent(message.getContent());
            messageDto.setCreatedTime(message.getCreatedTime());
            messageDto.setSenderName(message.getNameSender());
            messageDto.setReceiverName(message.getNameReceiver());
            messageDto.setSenderImage(message.getImageSender());
            messageDto.setReceiverImage(message.getImageReceiver());
            messageDtos.add(messageDto);
        }
        return messageDtos;

    }

    public Timestamp findMaxTimestamp(MessageList messageList) {

        if(messageList.getUpdatedTime() != null) return messageList.getUpdatedTime();
        else return messageList.getCreatedTime();

    }

    public List<UserProfileDto> convertUserProfileListToUserProfileDtoList(List<UserProfile> userProfileList) {
        List<UserProfileDto> userProfileDtoList = new ArrayList<>();
        for(UserProfile userProfile : userProfileList) {
            UserProfileDto userProfileDto = new UserProfileDto();
            userProfileDto.setAge(userProfile.getAge());
            userProfileDto.setCityAddress(userProfile.getCityAddress());
            userProfileDto.setFilmCategoryFavourite(userProfile.getFilmCategoryFavourite());
            userProfileDto.setFullName(userProfile.getFullname());
            userProfileDto.setGameCategoryFavourite(userProfile.getGameCategoryFavourite());
            userProfileDto.setGender(userProfile.getGender());
            userProfileDto.setMusicCategoryFavourite(userProfile.getMusicCategoryFavourite());
            userProfileDto.setSportCategoryFavourite(userProfile.getSportCategoryFavourite());
            userProfileDto.setPoint(0);
            userProfileDto.setImageProfile(userProfile.getImageProfile());
            userProfileDtoList.add(userProfileDto);
        }
        return userProfileDtoList;
    }

    public List<UserProfileDto> checkPointObjectList(UserDesireDto userDesireDto, List<UserProfileDto> userProfileDtoList) {

        for(UserProfileDto userProfileDto : userProfileDtoList) {
            int point = 0;
            if(userDesireDto.getAge() == userProfileDto.getAge()) point++;
            if(userDesireDto.getCityAddress().equals(userProfileDto.getCityAddress())) point++;
            if(userDesireDto.getFilmCategoryFavourite().equals(userProfileDto.getFilmCategoryFavourite())) point++;
            if(userDesireDto.getGameCategoryFavourite().equals(userProfileDto.getGameCategoryFavourite())) point++;
            if(userDesireDto.getGender().equals(userProfileDto.getGender())) point++;
            if(userDesireDto.getMusicCategoryFavourite().equals(userProfileDto.getMusicCategoryFavourite())) point++;
            if(userDesireDto.getSportCategoryFavourite().equals(userProfileDto.getSportCategoryFavourite())) point++;
            userProfileDto.setPoint(point);
        }
        return userProfileDtoList;

    }

    public List<UserProfileDto> sortByPoint(List<UserProfileDto> userProfileDtoList) {

        Collections.sort(userProfileDtoList, new Comparator<UserProfileDto>() {
            @Override
            public int compare(UserProfileDto lupd, UserProfileDto rupd) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lupd.getPoint() > rupd.getPoint() ? -1 : (lupd.getPoint() < rupd.getPoint()) ? 1 : 0;
            }
        });
        return userProfileDtoList;

    }

}
