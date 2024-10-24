package com.aakulova.letsevent.user;

import com.aakulova.letsevent.event.ListData;

import java.util.Objects;

public class NoticeListData {
    private String name, desc;
    private int image;

    public NoticeListData(String name, String desc, int image) {
        this.name = name;
        this.desc = desc;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        NoticeListData noticeListData = (NoticeListData) obj;
        return name.equals(noticeListData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc);
    }

}
