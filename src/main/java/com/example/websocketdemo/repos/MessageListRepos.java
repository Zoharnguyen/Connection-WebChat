package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.MessageList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageListRepos extends CrudRepository<MessageList, UUID> {

    @Query(nativeQuery = true, value = "select * from message_list where name_sender = ?1 and name_receiver = ?2")
    MessageList getMessageListBySenderAndReceiverName(String senderName, String receiverName);

    @Query(nativeQuery = true, value = "select * from message_list where name_receiver = ?1")
    List<MessageList> getAllMessageListByReceiverName(String receiverName);

}
