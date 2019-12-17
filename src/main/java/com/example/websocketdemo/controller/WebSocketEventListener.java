package com.example.websocketdemo.controller;

import com.example.websocketdemo.entity.MessageList;
import com.example.websocketdemo.model.ChatMessage;
import com.example.websocketdemo.service.CommonService;
import com.example.websocketdemo.service.MessageListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MessageListService messageListService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String senderName = (String) headerAccessor.getSessionAttributes().get("senderName");
        String receiverName = (String) headerAccessor.getSessionAttributes().get("receiverName");
        String content = (String) headerAccessor.getSessionAttributes().get("message");
        String imageSender = (String) headerAccessor.getSessionAttributes().get("imageSender");
        String imageReceiver = (String) headerAccessor.getSessionAttributes().get("imageReceiver");
        MessageList messageList = messageListService.getMessageListBySenderAndReceiverName(senderName,receiverName);
        if(messageList == null) messageList = new MessageList();
        messageList.setContent(content);
        messageList.setNameReceiver(receiverName);
        messageList.setNameSender(senderName);
        messageList.setImageSender(imageSender);
        messageList.setImageReceiver(imageReceiver);
        messageListService.save(messageList);

        if(senderName != null) {
            logger.info("User Disconnected : " + senderName);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(senderName);
            chatMessage.setReceiver(receiverName);

            String destination = "/topic/public";
            if(commonService.compareString(chatMessage.getSender(),chatMessage.getReceiver())){
                destination = "/topic/private" + chatMessage.getSender() + chatMessage.getReceiver();
            } else destination = "/topic/private" + chatMessage.getReceiver() + chatMessage.getSender();

            messagingTemplate.convertAndSend(destination, chatMessage);
        }
    }
}
