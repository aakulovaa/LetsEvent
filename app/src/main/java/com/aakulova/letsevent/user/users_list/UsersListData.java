package com.aakulova.letsevent.user.users_list;

import java.util.Objects;

public class UsersListData {
    private String name;
    private int image;

    public UsersListData(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        UsersListData noticeListData = (UsersListData) obj;
        return name.equals(noticeListData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
