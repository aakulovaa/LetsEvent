package com.aakulova.letsevent.user;

public class NewsListData {
    private String userLog, nameNews, descNews;
    private int imageUser, imageNews;

    public NewsListData(String userLog, String nameNews, String descNews, int imageUser, int imageNews) {
        this.userLog = userLog;
        this.nameNews = nameNews;
        this.descNews = descNews;
        this.imageUser = imageUser;
        this.imageNews = imageNews;
    }

    public String getUserLog() {
        return userLog;
    }

    public void setUserLog(String userLog) {
        this.userLog = userLog;
    }

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public String getDescNews() {
        return descNews;
    }

    public void setDescNews(String descNews) {
        this.descNews = descNews;
    }

    public int getImageUser() {
        return imageUser;
    }

    public void setImageUser(int imageUser) {
        this.imageUser = imageUser;
    }

    public int getImageNews() {
        return imageNews;
    }

    public void setImageNews(int imageNews) {
        this.imageNews = imageNews;
    }
}
