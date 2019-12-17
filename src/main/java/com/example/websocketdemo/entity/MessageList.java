package com.example.websocketdemo.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "message_list")
@NoArgsConstructor
public class MessageList {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "content")
    private String content;

    @Column(name = "name_sender")
    private String nameSender;

    @Column(name = "name_receiver")
    private String nameReceiver;

    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp createdTime;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "image_receiver")
    private String imageReceiver;

    @Column(name = "image_sender")
    private String imageSender;

    public String getImageReceiver() {
        return imageReceiver;
    }

    public void setImageReceiver(String imageReceiver) {
        this.imageReceiver = imageReceiver;
    }

    public String getImageSender() {
        return imageSender;
    }

    public void setImageSender(String imageSender) {
        this.imageSender = imageSender;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

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
}
