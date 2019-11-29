package com.example.websocketdemo.service.impl;

import com.example.websocketdemo.entity.TypePerson;
import com.example.websocketdemo.repos.TypePersonRepos;
import com.example.websocketdemo.service.TypePersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TypePersonServiceImpl implements TypePersonService {

    @Autowired
    TypePersonRepos typePersonRepos;

    @Override
    public List<TypePerson> getTypePersons() {
        List<TypePerson> typePersonList = new ArrayList<>();
        typePersonRepos.findAll().forEach(typePersonList::add);
        return typePersonList;
    }

}
