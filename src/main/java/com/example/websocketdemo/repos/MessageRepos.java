package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepos extends CrudRepository<Message, UUID> {

    @Query(nativeQuery = true, value = "select * from message where name_sender=?1 and name_receiver=?2")
    List<Message> getListBySenderAndReceiverName(String senderName, String receiverName);

}
