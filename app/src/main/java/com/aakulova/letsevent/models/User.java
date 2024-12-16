package com.aakulova.letsevent.models;

import java.security.SecureRandom;

public class User {
    private final String idUser;
    private String username; // Логин пользователя
    private String email;
    private String password;
    private String repPass;
    private boolean isAuthenticated;
    private String profileImageUrl; // URL изображения профиля
    private String accountType; // "regular" или "business"
    private int followersCount; // Количество подписчиков
    private int followingCount;  // Количество подписок
    private int attendedEventsCount; // Количество посещенных мероприятий
    private int publishedEventsCount; // Количество опубликованных мероприятий

    public User(String id, String username, String email, String password, String repPass, boolean isAuthenticated, String profileImageUrl, String accountType, int followersCount, int followingCount, int attendedEventsCount, int publishedEventsCount) {
        this.idUser = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.repPass = repPass;
        this.isAuthenticated = isAuthenticated;
        this.profileImageUrl = profileImageUrl;
        this.accountType = accountType;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.attendedEventsCount = attendedEventsCount;
        this.publishedEventsCount = publishedEventsCount;
    }

    public User(String id, String username, String email, String profileImageUrl, String accountType) {
        this.idUser = id;
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.accountType = accountType;
        this.followersCount = 0; // Изначально 0
        this.followingCount = 0; // Изначально 0
        this.attendedEventsCount = 0; // Изначально 0
        this.publishedEventsCount = 0; // Изначально 0
        this.isAuthenticated = false;
    }

    public User(String id, String username, String email, String password, String repPass, String profileImageUrl, String accountType) {
        this.idUser = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.repPass = repPass;
        this.profileImageUrl = profileImageUrl;
        this.accountType = accountType;
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


    public String getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

