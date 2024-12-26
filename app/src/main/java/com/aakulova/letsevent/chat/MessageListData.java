package com.aakulova.letsevent.chat;

import java.util.Objects;

public class MessageListData {
    private String sendingUser,time, message;

    public MessageListData(String sendingUser, String time, String message) {
        this.sendingUser = sendingUser;
        this.time = time;
        this.message = message;
    }

    public String getSendingUser() {
        return sendingUser;
    }

    public void setSendingUser(String sendingUser) {
        this.sendingUser = sendingUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String desc) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MessageListData noticeListData = (MessageListData) obj;
        return sendingUser.equals(noticeListData.sendingUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sendingUser, message);
    }

}
