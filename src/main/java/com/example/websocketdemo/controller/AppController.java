package com.example.websocketdemo.controller;

import com.example.websocketdemo.constant.Constant;
import com.example.websocketdemo.dto.UserDesireDto;
import com.example.websocketdemo.dto.UserDto;
import com.example.websocketdemo.dto.UserProfileDto;
import com.example.websocketdemo.entity.PersonDesire;
import com.example.websocketdemo.entity.UserInformationBasic;
import com.example.websocketdemo.entity.UserProfile;
import com.example.websocketdemo.model.ChatMessage;
import com.example.websocketdemo.repos.PersonDesireRepos;
import com.example.websocketdemo.service.*;
import com.example.websocketdemo.wrapper.WrapperList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/app")
public class AppController {

    public UUID idUser;
    public int count = 0;
    public UserDto userDtoDefault;
    public WrapperList objectListDefault = new WrapperList();
    public UserDesireDto nameObjectPicked;

    @Autowired
    MessageService messageService;

    @Autowired
    UserInformationBasicService userInformationBasicService;

    @Autowired
    CommonService commonService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private PersonDesireService personDesireService;

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping("/welcome")
    public String welCome(){
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        UserDto userDto = new UserDto("Male");
        model.addAttribute("userDto", userDto);
        return "login";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "signup";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(@Valid @ModelAttribute("userDto") UserDto userDto,Model model) {

        UserInformationBasic userInformationBasic = userInformationBasicService.findByUserName(userDto.getUserName());
        model.addAttribute("nameObjectUser", new UserDesireDto());
        model.addAttribute("userDesireDto", new UserDesireDto());
        idUser = userInformationBasic.getId();
        initialeModelAttributeList(model, idUser);

        if(userInformationBasic!=null && userDto.getPassword().equals(userInformationBasic.getPassword())){
            UserProfile userProfile = userInformationBasic.getUserProfile();
            userDto.setAge(userProfile.getAge());
            userDto.setCityAddress(userProfile.getCityAddress());
            userDto.setFilmCategoryFavourite(userProfile.getFilmCategoryFavourite());
            userDto.setMusicCategoryFavourite(userProfile.getMusicCategoryFavourite());
            userDto.setSportCategoryFavourite(userProfile.getSportCategoryFavourite());
            userDto.setGameCategoryFavourite(userProfile.getGameCategoryFavourite());
            userDto.setGender(userProfile.getGender());
            userDto.setIdUser(idUser);
            userDto.setPersonDesires(userInformationBasic.getPersonDesires());
            model.addAttribute("profileStatus","content_home");
            userDtoDefault = userDto;
            return "profile";
        } else return "welcome";
    }

    @RequestMapping(value = "/profile_content_profile_edit", method = RequestMethod.GET)
    public String profileContentProfileEdit(@Valid @ModelAttribute("userDto") UserDto userDto, Model model) {
        initialeModelAttributeList(model, idUser);
        userDto.setIdUser(idUser);
        commonService.editProfile(userDto);
        model.addAttribute("profileStatus","content_profile_edit");
        return "profile";
    }

    @RequestMapping(value = "/profile_content_standard_create", method = RequestMethod.GET)
    public String profileContentStandardCreate(@Valid @ModelAttribute("userDesireDto") UserDesireDto userDesireDto,Model model) {
        initialeModelAttributeList(model, idUser);
        UserInformationBasic userInformationBasic = userInformationBasicService.findById(idUser);
        if(checkNameExist(userDesireDto.getNameObject(),idUser) == false && userInformationBasic != null) {
            PersonDesire personDesire = passUserDesireDtoToPersonDesire(userDesireDto);
            personDesire.setUserInformationBasic(userInformationBasic);
            personDesireService.save(personDesire);
            count++;
            System.out.println("Count: " + count);
        }
        model.addAttribute("profileStatus","content_home");
        return "profile";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(@Valid @ModelAttribute("nameObjectUser") UserDesireDto nameObjectUser, Model model) {
        initialeModelAttributeList(model, idUser);
        PersonDesire personDesire = personDesireService.findByNameAndSpecificUser(idUser,nameObjectUser.getNameObject());
        UserDesireDto userDesireDtoShow = passPersonDesireToUserDesireDto(personDesire);
        model.addAttribute("userDesireDtoShow", userDesireDtoShow);
        model.addAttribute("profileStatus","list");
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("userDesireDto", new UserDesireDto());
        nameObjectPicked = nameObjectUser;
        return "profile";
    }

    @RequestMapping(value = "/show_suitableness_profiles", method = RequestMethod.GET)
    public String showSuitablenessProfiles(Model model) {
        initialeModelAttributeList(model, idUser);
        PersonDesire personDesire = personDesireService.findByNameAndSpecificUser(idUser,nameObjectPicked.getNameObject());
        UserDesireDto userDesireDtoShow = passPersonDesireToUserDesireDto(personDesire);
        List<UserProfile> userProfileList = userProfileService.getAllUserProfile();
        userProfileList.remove(userInformationBasicService.findById(idUser).getUserProfile());
        List<UserProfileDto> userProfileDtoList = commonService.sortUserProfileByObject(userProfileList, userDesireDtoShow);
        model.addAttribute("userDesireDtoShow", userDesireDtoShow);
        model.addAttribute("profileStatus","find_object");
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("userDesireDto", new UserDesireDto());
        model.addAttribute("userProfileSuitablenessList", userProfileDtoList);
        model.addAttribute("nameObjectUser",nameObjectPicked);
        return "profile";
    }

    @RequestMapping("/signup-check")
    public String signupCheck(@Valid @ModelAttribute("userDto") UserDto userDto) {
        UserInformationBasic userInformationBasic = userInformationBasicService.findByUserName(userDto.getUserName());
        if(userInformationBasic == null){
            commonService.signUp(userDto);
            return "profile";
        } else return "welcome";
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        simpMessagingTemplate.convertAndSend("/topic/public", chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        simpMessagingTemplate.convertAndSend("/topic/public", chatMessage);
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat() {
        return "chat";
    }

    private void initialeModelAttributeList(Model model, UUID idUser) {
        Constant constant = new Constant();
        WrapperList genderList = new WrapperList();
        genderList.setList(constant.genderList());
        model.addAttribute("genderList", genderList);
        WrapperList cityAddressList = new WrapperList();
        cityAddressList.setList(constant.cityAddressList());
        model.addAttribute("cityAddressList", cityAddressList);
        WrapperList filmCategoryFavouriteList = new WrapperList();
        filmCategoryFavouriteList.setList(constant.filmCategoryFavouriteList());
        model.addAttribute("filmCategoryFavouriteList", filmCategoryFavouriteList);
        WrapperList musicCategoryFavouriteList = new WrapperList();
        musicCategoryFavouriteList.setList(constant.musicCategoryFavouriteList());
        model.addAttribute("musicCategoryFavouriteList", musicCategoryFavouriteList);
        WrapperList gameCategoryFavouriteList = new WrapperList();
        gameCategoryFavouriteList.setList(constant.gameCategoryFavouriteList());
        model.addAttribute("gameCategoryFavouriteList", gameCategoryFavouriteList);
        WrapperList sportCategoryFavouriteList = new WrapperList();
        sportCategoryFavouriteList.setList(constant.sportCategoryFavouriteList());
        model.addAttribute("sportCategoryFavouriteList", sportCategoryFavouriteList);
        WrapperList objectList = new WrapperList();
        objectList.setList(personDesireService.getObjectNames(idUser));
        objectListDefault = objectList;
        model.addAttribute("objectList", objectList);
    }

    public boolean checkNameExist(String name, UUID idUser) {
        List<String> list = personDesireService.getObjectNames(idUser);
        if(list.contains(name)) return true;
        return false;
    }

    public PersonDesire passUserDesireDtoToPersonDesire(UserDesireDto userDesireDto) {
        PersonDesire personDesire = new PersonDesire();
        personDesire.setAge(userDesireDto.getAge());
        personDesire.setCityAddress(userDesireDto.getCityAddress());
        personDesire.setFilmCategoryFavourite(userDesireDto.getFilmCategoryFavourite());
        personDesire.setGameCategoryFavourite(userDesireDto.getGameCategoryFavourite());
        personDesire.setGender(userDesireDto.getGender());
        personDesire.setMusicCategoryFavourite(userDesireDto.getMusicCategoryFavourite());
        personDesire.setSpaceAge(2);
        personDesire.setSportFavourite(userDesireDto.getSportCategoryFavourite());
        personDesire.setName(userDesireDto.getNameObject());
        return personDesire;
    }

    public UserDesireDto passPersonDesireToUserDesireDto(PersonDesire personDesire) {
        UserDesireDto userDesireDto = new UserDesireDto();
        userDesireDto.setAge(personDesire.getAge());
        userDesireDto.setCityAddress(personDesire.getCityAddress());
        userDesireDto.setFilmCategoryFavourite(personDesire.getFilmCategoryFavourite());
        userDesireDto.setGameCategoryFavourite(personDesire.getGameCategoryFavourite());
        userDesireDto.setGender(personDesire.getGender());
        userDesireDto.setMusicCategoryFavourite(personDesire.getMusicCategoryFavourite());
        userDesireDto.setSportCategoryFavourite(personDesire.getSportFavourite());
        userDesireDto.setNameObject(personDesire.getName());
        return userDesireDto;
    }

}
