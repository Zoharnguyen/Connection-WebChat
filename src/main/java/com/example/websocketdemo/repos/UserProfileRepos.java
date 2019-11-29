package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepos extends CrudRepository<UserProfile, UUID> {

    @Query(value = "select * from user_profile where id_user = ?1", nativeQuery = true)
    UserProfile findByIdUser(UUID userId);

}
