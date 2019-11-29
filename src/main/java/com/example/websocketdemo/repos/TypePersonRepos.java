package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.TypePerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypePersonRepos extends CrudRepository<TypePerson, UUID> {

}
