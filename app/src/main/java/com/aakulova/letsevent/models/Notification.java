package com.aakulova.letsevent.models;

public class Notification {
    private Integer idNotification;
    private User userNotification;
    private String nameNotification;
    private int imgSrcNotification;

    public Notification(Integer idNotification, User userNotification, String nameNotification, int imgSrcNotification) {
        this.idNotification = idNotification;
        this.userNotification = userNotification;
        this.nameNotification = nameNotification;
        this.imgSrcNotification = imgSrcNotification;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public User getUserNotification() {
        return userNotification;
    }

    public void setUserNotification(User userNotification) {
        this.userNotification = userNotification;
    }

    public String getNameNotification() {
        return nameNotification;
    }

    public void setNameNotification(String nameNotification) {
        this.nameNotification = nameNotification;
    }

    public int getImgSrcNotification() {
        return imgSrcNotification;
    }

    public void setImgSrcNotification(int imgSrcNotification) {
        this.imgSrcNotification = imgSrcNotification;
    }
}
