package com.example.websocketdemo.service;

import com.example.websocketdemo.entity.PersonDesire;

import java.util.List;
import java.util.UUID;

public interface PersonDesireService {

    PersonDesire save(PersonDesire personDesire);
    List<String> getObjectNames(UUID idUser);
    PersonDesire findByNameAndSpecificUser(UUID idUser, String name);

}
