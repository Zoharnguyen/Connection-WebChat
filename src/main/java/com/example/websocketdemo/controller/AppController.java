package com.example.websocketdemo.controller;

import com.example.websocketdemo.constant.Constant;
import com.example.websocketdemo.dto.*;
import com.example.websocketdemo.entity.*;
import com.example.websocketdemo.model.ChatMessage;
import com.example.websocketdemo.model.UploadForm;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/app")
public class AppController {

    public UUID idUser;
    public int count = 0;
    public UserDto userDtoDefault;
    public WrapperList objectListDefault = new WrapperList();
    public UserDesireDto nameObjectPicked = new UserDesireDto();
    public String receiverName;
    public String content;


    @Autowired
    private MessageService messageService;

    @Autowired
    private UserInformationBasicService userInformationBasicService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private PersonDesireService personDesireService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private MessageListService messageListService;

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
        ChatUserInformationDto chatUserInformationDto = new ChatUserInformationDto();
        UploadForm uploadForm = new UploadForm();
        List<MessageList> messageLists = messageListService.getAllMessageListByReceiverName(userInformationBasic.getUserProfile().getFullname());
        messageLists = commonService.executeMessageLists(messageLists);

        model.addAttribute("messageLists", messageLists);
        model.addAttribute("uploadForm", uploadForm);
        model.addAttribute("chatUserInformationDto", chatUserInformationDto);
        model.addAttribute("nameObjectUser", new UserDesireDto());
        model.addAttribute("userDesireDto", new UserDesireDto());
        model.addAttribute("nameObjectUser",nameObjectPicked);
        model.addAttribute("uploadForm", new UploadForm());
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
            userDto.setFullName(userProfile.getFullname());
            userDto.setImageProfile(userProfile.getImageProfile());
            userDto.setImageBackground(userProfile.getImageBackground());
            model.addAttribute("profileStatus","content_home");
            userDtoDefault = userDto;
            return "profile";
        } else return "welcome";
    }

    @RequestMapping(value = "/profile_content_profile_edit", method = RequestMethod.GET)
    public String profileContentProfileEdit(@Valid @ModelAttribute("userDto") UserDto userDto, Model model,HttpServletRequest request) throws IOException {
        initialeModelAttributeList(model, idUser);
        userDto.setIdUser(idUser);
        List<UserProfile> userProfileList = userProfileService.getAllUserProfile();
        userProfileList.remove(userInformationBasicService.findById(idUser).getUserProfile());

        if(userDto.getFiles() != null) {
            String urlFile = doUpload(request, model, userDto.getFiles());
            userDto.setImageProfile(urlFile);
        }

        List<MessageList> messageLists = messageListService.getAllMessageListByReceiverName(userDtoDefault.getFullName());
        messageLists = commonService.executeMessageLists(messageLists);
        userDtoDefault = userDto;

        model.addAttribute("messageLists", messageLists);
        model.addAttribute("userDesireDto", new UserDesireDto());
        model.addAttribute("nameObjectUser",nameObjectPicked);
        commonService.editProfile(userDto);
        model.addAttribute("profileStatus","content_profile_edit");
        model.addAttribute("chatUserInformationDto", new ChatUserInformationDto());
        userDtoDefault.setImageProfile(userInformationBasicService.findById(idUser).getUserProfile().getImageProfile());
        userDtoDefault.setImageBackground(userInformationBasicService.findById(idUser).getUserProfile().getImageBackground());
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("uploadForm", new UploadForm());
        return "profile";
    }

    @RequestMapping(value = "/profile_content_standard_create", method = RequestMethod.GET)
    public String profileContentStandardCreate(@Valid @ModelAttribute("userDesireDto") UserDesireDto userDesireDto,Model model) {
        UserInformationBasic userInformationBasic = userInformationBasicService.findById(idUser);
        if(checkNameExist(userDesireDto.getNameObject(),idUser) == false && userInformationBasic != null) {
            PersonDesire personDesire = passUserDesireDtoToPersonDesire(userDesireDto);
            personDesire.setUserInformationBasic(userInformationBasic);
            personDesireService.save(personDesire);
            count++;
            System.out.println("Count: " + count);
        }
        List<UserProfile> userProfileList = userProfileService.getAllUserProfile();
        userProfileList.remove(userInformationBasicService.findById(idUser).getUserProfile());
        List<MessageList> messageLists = messageListService.getAllMessageListByReceiverName(userDtoDefault.getFullName());
        messageLists = commonService.executeMessageLists(messageLists);

        model.addAttribute("messageLists", messageLists);
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("userDesireDto", new UserDesireDto());
        model.addAttribute("nameObjectUser",nameObjectPicked);
        model.addAttribute("chatUserInformationDto", new ChatUserInformationDto());
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("profileStatus","show_list");
        model.addAttribute("uploadForm", new UploadForm());
        initialeModelAttributeList(model, idUser);
        return "profile";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(@Valid @ModelAttribute("nameObjectUser") UserDesireDto nameObjectUser, Model model) {
        initialeModelAttributeList(model, idUser);
        PersonDesire personDesire = personDesireService.findByNameAndSpecificUser(idUser,nameObjectUser.getNameObject());
        UserDesireDto userDesireDtoShow = passPersonDesireToUserDesireDto(personDesire);
        UploadForm uploadForm = new UploadForm();
        List<MessageList> messageLists = messageListService.getAllMessageListByReceiverName(userDtoDefault.getFullName());
        messageLists = commonService.executeMessageLists(messageLists);

        model.addAttribute("messageLists", messageLists);
        model.addAttribute("chatUserInformationDto", new ChatUserInformationDto());
        model.addAttribute("uploadForm", uploadForm);
        model.addAttribute("userDesireDtoShow", userDesireDtoShow);
        model.addAttribute("profileStatus","list");
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("userDesireDto", new UserDesireDto());
        model.addAttribute("uploadForm", new UploadForm());
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
        UploadForm uploadForm = new UploadForm();
        List<MessageList> messageLists = messageListService.getAllMessageListByReceiverName(userDtoDefault.getFullName());
        messageLists = commonService.executeMessageLists(messageLists);

        model.addAttribute("messageLists", messageLists);
        model.addAttribute("uploadForm", uploadForm);
        model.addAttribute("userDesireDtoShow", userDesireDtoShow);
        model.addAttribute("profileStatus","find_object");
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("userDesireDto", new UserDesireDto());
        model.addAttribute("userProfileSuitablenessList", userProfileDtoList);
        model.addAttribute("nameObjectUser",nameObjectPicked);
        model.addAttribute("chatUserInformationDto", new ChatUserInformationDto());
        return "profile";
    }

    @RequestMapping(value = "/show_chat_specification", method = RequestMethod.GET)
    public String showChatSpecification(Model model) {
        initialeModelAttributeList(model, idUser);
        PersonDesire personDesire = personDesireService.findByNameAndSpecificUser(idUser,nameObjectPicked.getNameObject());
        UserDesireDto userDesireDtoShow = passPersonDesireToUserDesireDto(personDesire);
        List<UserProfile> userProfileList = userProfileService.getAllUserProfile();
        userProfileList.remove(userInformationBasicService.findById(idUser).getUserProfile());
        List<UserProfileDto> userProfileDtoList = commonService.sortUserProfileByObject(userProfileList, userDesireDtoShow);
        UploadForm uploadForm = new UploadForm();
        List<MessageList> messageLists = messageListService.getAllMessageListByReceiverName(userDtoDefault.getFullName());
        messageLists = commonService.executeMessageLists(messageLists);

        model.addAttribute("messageLists", messageLists);
        model.addAttribute("uploadForm", uploadForm);
        model.addAttribute("userDesireDtoShow", userDesireDtoShow);
        model.addAttribute("profileStatus","chat-page");
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("userDesireDto", new UserDesireDto());
        model.addAttribute("userProfileSuitablenessList", userProfileDtoList);
        model.addAttribute("nameObjectUser",nameObjectPicked);
        model.addAttribute("uploadForm", new UploadForm());
        return "profile";
    }

    @RequestMapping(value = "/show_chat_specification_detail", method = RequestMethod.GET)
    public String showChatSpecificationDetail(@ModelAttribute("chatUserInformationDto") ChatUserInformationDto chatUserInformationDto, Model model) {
        initialeModelAttributeList(model, idUser);
        List<UserProfile> userProfileList = userProfileService.getAllUserProfile();
        userProfileList.remove(userInformationBasicService.findById(idUser).getUserProfile());
        List<Message> messageList01 = messageService.getListBySenderAndReceiverName(chatUserInformationDto.getSenderName(), chatUserInformationDto.getReceiverName());
        List<Message> messageList02 = messageService.getListBySenderAndReceiverName(chatUserInformationDto.getReceiverName(), chatUserInformationDto.getSenderName());
        List<MessageDto> messageDtos = commonService.sortMessageByTime(messageList01, messageList02);
        List<MessageList> messageLists = messageListService.getAllMessageListByReceiverName(userDtoDefault.getFullName());
        messageLists = commonService.executeMessageLists(messageLists);
        UploadForm uploadForm = new UploadForm();
        List<MessageList> messageListss = messageListService.getAllMessageListByReceiverName(userDtoDefault.getFullName());
        messageListss = commonService.executeMessageLists(messageListss);

        model.addAttribute("messageLists", messageListss);
        model.addAttribute("uploadForm", uploadForm);
        model.addAttribute("messageLists", messageLists);
        model.addAttribute("messageDtos", messageDtos);
        model.addAttribute("profileStatus","chat-page-detail");
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("userDesireDto", new UserDesireDto());
        model.addAttribute("nameObjectUser",nameObjectPicked);
        model.addAttribute("uploadForm", new UploadForm());
        return "profile";
    }

    @RequestMapping("/signup-check")
    public String signupCheck(@Valid @ModelAttribute("userDto") UserDto userDto, Model model) {
        model.addAttribute("chatUserInformationDto", new ChatUserInformationDto());
        UserInformationBasic userInformationBasic = userInformationBasicService.findByUserName(userDto.getUserName());
        if(userInformationBasic == null){
            commonService.signUp(userDto);
            return "profile";
        } else return "welcome";
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String destination = "/topic/public";
        Message message = new Message();
        if(commonService.compareString(chatMessage.getSender(),chatMessage.getReceiver())){
            destination = "/topic/private" + chatMessage.getSender() + chatMessage.getReceiver
                    ();
        } else {
            destination = "/topic/private" + chatMessage.getReceiver() + chatMessage.getSender();
        }
        System.out.println("Destination Chat: " + destination);
        message.setNameSender(chatMessage.getSender());
        message.setNameReceiver(chatMessage.getReceiver());
        message.setContent(chatMessage.getContent());
        message.setImageSender(chatMessage.getImageSender());
        message.setImageReceiver(chatMessage.getImageReceiver());
        if(chatMessage.getReceiver() != null) {
            receiverName = chatMessage.getReceiver();
        }
        content = chatMessage.getContent();
        headerAccessor.getSessionAttributes().put("senderName", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("receiverName", receiverName);
        headerAccessor.getSessionAttributes().put("message", content);
        headerAccessor.getSessionAttributes().put("imageSender", chatMessage.getImageSender());
        headerAccessor.getSessionAttributes().put("imageReceiver", chatMessage.getReceiver());

        messageService.saveMessage(message);
        simpMessagingTemplate.convertAndSend(destination, chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage) {
        // Add username in web socket session
        String destination = "/topic/public";
        if(commonService.compareString(chatMessage.getSender(),chatMessage.getReceiver())){
            destination = "/topic/private" + chatMessage.getSender() + chatMessage.getReceiver();
        } else destination = "/topic/private" + chatMessage.getReceiver() + chatMessage.getSender();
        System.out.println("Destination Add User: " + destination);
        simpMessagingTemplate.convertAndSend(destination, chatMessage);
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat() {
        return "chat";
    }

    @RequestMapping(value= "/upload-one-file", method=RequestMethod.POST)
    public String uploadFileHandlerPost(HttpServletRequest request, Model model, @ModelAttribute("uploadForm") UploadForm uploadForm) throws IOException {
        initialeModelAttributeList(model, idUser);
        List<UserProfile> userProfileList = userProfileService.getAllUserProfile();
        userProfileList.remove(userInformationBasicService.findById(idUser).getUserProfile());
        String urlFile = doUpload(request, model, uploadForm.getFiles());
        userDtoDefault.setImageProfile(urlFile);
        UserProfile userProfile = userProfileService.findByIdUser(userDtoDefault.getIdUser());
        userProfile.setImageProfile(urlFile);
        userProfileService.save(userProfile);

        List<MessageList> messageLists = messageListService.getAllMessageListByReceiverName(userDtoDefault.getFullName());
        messageLists = commonService.executeMessageLists(messageLists);

        model.addAttribute("messageLists", messageLists);
        model.addAttribute("profileStatus","content_home");
        model.addAttribute("userDto", userDtoDefault);
        model.addAttribute("userDesireDto", new UserDesireDto());
        model.addAttribute("nameObjectUser",nameObjectPicked);

        return "profile";
    }

    public String doUpload(HttpServletRequest request, Model model, MultipartFile[] files) throws IOException {
        String uploadRootPath= "C:/xampp/htdocs/applications/ConnectionApp/";
        String urlFile = "";
        for(MultipartFile file : files) {
            String nameOrigin = file.getOriginalFilename();
            urlFile = "http://localhost:8081/applications/ConnectionApp/" + nameOrigin;
            if(nameOrigin!=null && nameOrigin.length() > 0) {
                File fileServer = new File( uploadRootPath + nameOrigin);
                try {
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileServer));
                    stream.write(file.getBytes());
                    stream.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return urlFile;
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
