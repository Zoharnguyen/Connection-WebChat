package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepos extends CrudRepository<UserProfile, UUID> {
}
