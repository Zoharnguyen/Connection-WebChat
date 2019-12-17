package com.example.websocketdemo.dto;

import java.sql.Timestamp;

public class MessageDto implements Comparable<MessageDto>{

    private String senderName;
    private String receiverName;
    private String content;
    private String senderImage;
    private String receiverImage;
    private Timestamp createdTime;

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }

    public String getReceiverImage() {
        return receiverImage;
    }

    public void setReceiverImage(String receiverImage) {
        this.receiverImage = receiverImage;
    }

    @Override
    public int compareTo(MessageDto o) {
        if(createdTime.after(o.createdTime)) return 1;
        else if(createdTime.before(o.createdTime)) return -1;
        return 0;
    }
}
