package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.UserInformationBasic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserInformationBasicRepos extends CrudRepository<UserInformationBasic, UUID> {

    @Query(value = "select user from UserInformationBasic user where userName = ?1")
    UserInformationBasic findByUserName(String userName);

}
