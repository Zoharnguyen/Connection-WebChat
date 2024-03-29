package com.example.websocketdemo.service.impl;

import com.example.websocketdemo.entity.Message;
import com.example.websocketdemo.repos.MessageRepos;
import com.example.websocketdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepos messageRepos;

    @Override
    public void saveMessage(Message message) {
        messageRepos.save(message);
    }

    @Override
    public List<Message> getListBySenderAndReceiverName(String senderName, String receiverName) {
        return messageRepos.getListBySenderAndReceiverName(senderName,receiverName);
    }

}
