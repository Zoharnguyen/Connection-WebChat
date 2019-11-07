package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.UserInformationBasic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserInformationBasicRepos extends CrudRepository<UserInformationBasic, UUID> {
}
