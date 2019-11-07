package com.example.websocketdemo.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_member")
    private Integer numberMember;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_conversation",
            joinColumns = {@JoinColumn(name = "id_conversation")},
            inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private Set<UserInformationBasic> users;

    @OneToMany(
            mappedBy = "conversation",
            cascade = CascadeType.ALL
    )
    private List<Message> messages = new ArrayList<>();

}
