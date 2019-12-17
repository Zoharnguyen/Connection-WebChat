package com.example.websocketdemo.service;

import com.example.websocketdemo.entity.Message;

import java.util.List;

public interface MessageService {

    public void saveMessage(Message message);
    public List<Message> getListBySenderAndReceiverName(String senderName, String receiverName);

}
