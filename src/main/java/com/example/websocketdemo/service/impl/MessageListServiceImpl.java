package com.example.websocketdemo.service.impl;

import com.example.websocketdemo.entity.MessageList;
import com.example.websocketdemo.repos.MessageListRepos;
import com.example.websocketdemo.service.MessageListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageListServiceImpl implements MessageListService {

    @Autowired
    MessageListRepos messageListRepos;

    @Override
    public MessageList getMessageListBySenderAndReceiverName(String senderName, String receiverName) {
        return messageListRepos.getMessageListBySenderAndReceiverName(senderName, receiverName);
    }

    @Override
    public MessageList save(MessageList messageList) {
        return messageListRepos.save(messageList);
    }

    @Override
    public List<MessageList> getAllMessageList() {

        Iterable<MessageList> messageListIterable = messageListRepos.findAll();
        List<MessageList> messageLists = new ArrayList<>();
        messageListIterable.forEach(messageLists::add);
        return messageLists;

    }

    @Override
    public List<MessageList> getAllMessageListByReceiverName(String receiverName) {
        return messageListRepos.getAllMessageListByReceiverName(receiverName);
    }

}
