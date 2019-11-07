package com.example.websocketdemo.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "message")
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sender")
    private UserInformationBasic user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conversation")
    private Conversation conversation;

    public void setContent(String content) {
        this.content = content;
    }

}
