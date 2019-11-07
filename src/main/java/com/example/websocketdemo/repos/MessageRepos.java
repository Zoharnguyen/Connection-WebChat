package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepos extends CrudRepository<Message, UUID> {
}
