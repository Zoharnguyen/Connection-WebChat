package com.example.websocketdemo.service;

import com.example.websocketdemo.entity.MessageList;

import java.util.List;

public interface MessageListService {

    MessageList getMessageListBySenderAndReceiverName(String senderName, String receiverName);
    MessageList save(MessageList messageList);
    List<MessageList> getAllMessageList();
    List<MessageList> getAllMessageListByReceiverName(String receiverName);

}
