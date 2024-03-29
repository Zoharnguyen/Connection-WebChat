package com.example.websocketdemo.entity;

import lombok.NoArgsConstructor;
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

    @Column(name = "name_sender")
    private String nameSender;

    @Column(name = "name_receiver")
    private String nameReceiver;

    @Column(name = "image_sender")
    private String imageSender;

    @Column(name = "image_receiver")
    private String imageReceiver;

    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sender")
    private UserInformationBasic user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conversation")
    private Conversation conversation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public void setNameReceiver(String nameReceiver) {
        this.nameReceiver = nameReceiver;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public UserInformationBasic getUser() {
        return user;
    }

    public void setUser(UserInformationBasic user) {
        this.user = user;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getImageSender() {
        return imageSender;
    }

    public void setImageSender(String imageSender) {
        this.imageSender = imageSender;
    }

    public String getImageReceiver() {
        return imageReceiver;
    }

    public void setImageReceiver(String imageReceiver) {
        this.imageReceiver = imageReceiver;
    }
}
