package com.aakulova.letsevent.user;

import java.util.Objects;

public class DialogListData {
    private String name, message;
    private int image;

    public DialogListData(String name, String message, int image) {
        this.name = name;
        this.message = message;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String desc) {
        this.message = message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DialogListData noticeListData = (DialogListData) obj;
        return name.equals(noticeListData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, message);
    }

}
