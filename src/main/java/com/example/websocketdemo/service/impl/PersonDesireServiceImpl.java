package com.example.websocketdemo.service.impl;

import com.example.websocketdemo.entity.PersonDesire;
import com.example.websocketdemo.repos.PersonDesireRepos;
import com.example.websocketdemo.service.PersonDesireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDesireServiceImpl implements PersonDesireService {

    @Autowired
    PersonDesireRepos personDesireRepos;

    @Override
    public PersonDesire save(PersonDesire personDesire) {
        return personDesireRepos.save(personDesire);
    }

}
