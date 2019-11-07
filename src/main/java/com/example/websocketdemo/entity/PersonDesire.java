package com.example.websocketdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "person_desire")
@NoArgsConstructor
@Setter
@Getter
public class PersonDesire {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column(name = "gender")
    private String gender;

    @Column
    private String age;

    @Column
    private String spaceAge;

    @Column
    private String cityAddress;

    @Column
    private String sportFavourite;

    @Column
    private String filmCategoryFavourite;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserInformationBasic userInformationBasic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private TypePerson typePerson;

}
