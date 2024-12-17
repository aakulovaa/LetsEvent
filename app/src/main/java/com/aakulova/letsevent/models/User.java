package com.aakulova.letsevent.models;

import java.security.SecureRandom;

public class User {
    private final Integer idUser;
    private String loginUser; // Логин пользователя
    private String emailUser;
    private String passwordUser;
    private String repPass;
    private boolean isAuthenticated;
    private String accountTypeUser; // "regular" или "business"
    private String profileImageUrl; // URL изображения профиля
   private int followersCount; // Количество подписчиков
    private int followingCount;  // Количество подписок
    private int attendedEventsCount; // Количество посещенных мероприятий
    private int publishedEventsCount; // Количество опубликованных мероприятий

    public User(Integer id, String loginUser, String emailUser, String passwordUser, String repPass, boolean isAuthenticated, String profileImageUrl, String accountTypeUser, int followersCount, int followingCount, int attendedEventsCount, int publishedEventsCount) {
        this.idUser = id;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.repPass = repPass;
        this.isAuthenticated = isAuthenticated;
        this.profileImageUrl = profileImageUrl;
        this.accountTypeUser = accountTypeUser;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.attendedEventsCount = attendedEventsCount;
        this.publishedEventsCount = publishedEventsCount;
    }

    public User(Integer id, String loginUser, String emailUser, String profileImageUrl, String accountTypeUser) {
        this.idUser = id;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.profileImageUrl = profileImageUrl;
        this.accountTypeUser = accountTypeUser;
        this.followersCount = 0; // Изначально 0
        this.followingCount = 0; // Изначально 0
        this.attendedEventsCount = 0; // Изначально 0
        this.publishedEventsCount = 0; // Изначально 0
        this.isAuthenticated = false;
    }

    public User(Integer id, String loginUser, String emailUser, String passwordUser, String repPass, String profileImageUrl, String accountTypeUser) {
        this.idUser = id;
        this.loginUser = loginUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.repPass = repPass;
        this.profileImageUrl = profileImageUrl;
        this.accountTypeUser = accountTypeUser;
        this.followersCount = 0; // Изначально 0
        this.followingCount = 0; // Изначально 0
        this.attendedEventsCount = 0; // Изначально 0
        this.publishedEventsCount = 0; // Изначально 0
        this.isAuthenticated = false;
    }

    // Метод для генерации случайного логина
    public static String generateRandomUsername() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder username = new StringBuilder(8); // Длина логина 8 символов

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            username.append(characters.charAt(index));
        }

        return username.toString();
    }

    // Метод для генерации уникального ID
    public static String generateUniqueId() {
        return String.valueOf(System.currentTimeMillis()); // Используем текущее время в миллисекундах
    }


    public Integer getIdUser() {
        return idUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getAccountTypeUser() {
        return accountTypeUser;
    }

    public void setAccountTypeUser(String accountTypeUser) {
        this.accountTypeUser = accountTypeUser;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getAttendedEventsCount() {
        return attendedEventsCount;
    }

    public void setAttendedEventsCount(int attendedEventsCount) {
        this.attendedEventsCount = attendedEventsCount;
    }

    public void incrementAttendedEventsCount() {
        attendedEventsCount++;
    }

    public void decrementAttendedEventsCount() {
        if (attendedEventsCount > 0) attendedEventsCount--;
    }

    public void incrementPublishedEventsCount() {
        publishedEventsCount++;
    }

    public void decrementPublishedEventsCount() {
        if (publishedEventsCount > 0) publishedEventsCount--;
    }

    public int getPublishedEventsCount() {
        return publishedEventsCount;
    }

    public void setPublishedEventsCount(int publishedEventsCount) {
        this.publishedEventsCount = publishedEventsCount;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getRepPass() {
        return repPass;
    }

    public void setRepPass(String repPass) {
        this.repPass = repPass;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}

