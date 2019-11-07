package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.PersonDesire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonDesireRepos extends CrudRepository<PersonDesire, UUID> {
}
