package com.example.websocketdemo.repos;

import com.example.websocketdemo.entity.PersonDesire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonDesireRepos extends CrudRepository<PersonDesire, UUID> {

    @Query(nativeQuery = true, value = "select name from person_desire where id_user = ?1")
    List<String> getObjectNames(UUID idUser);

    @Query(nativeQuery = true, value = "select * from person_desire where id_user = ?1 and name = ?2")
    PersonDesire getObjectByName(UUID idUser, String name);

}
