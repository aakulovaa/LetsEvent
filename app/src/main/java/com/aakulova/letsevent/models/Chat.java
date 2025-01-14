package com.aakulova.letsevent.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Chat {
    private Integer idChat;
    private User userSender;
    private User userReceiver;
    private String messageChat;
    private String sendAt;

    public Chat(Integer idChat, User userSender, User userReceiver, String messageChat, String sendAt) {
        this.idChat = idChat;
        this.userSender = userSender;
        this.userReceiver = userReceiver;
        this.messageChat = messageChat;
        this.sendAt = sendAt;
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public User getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(User userReceiver) {
        this.userReceiver = userReceiver;
    }

    public String getMessageChat() {
        return messageChat;
    }

    public void setMessageChat(String messageChat) {
        this.messageChat = messageChat;
    }

    public String getSendAt() {
        return sendAt;
    }

    public void setSendAt(String sendAt) {
        this.sendAt = sendAt;
    }
}
