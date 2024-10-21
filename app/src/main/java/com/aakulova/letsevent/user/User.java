package com.aakulova.letsevent.user;

public class User {
    private String id;
    private String username; // Логин пользователя
    private String email;
    private String profileImageUrl; // URL изображения профиля
    private String accountType; // "regular" или "business"
    private int followersCount; // Количество подписчиков
    private int followingCount;  // Количество подписок
    private int attendedEventsCount; // Количество посещенных мероприятий


    public User(String id, String username, String email, String profileImageUrl, String accountType) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.accountType = accountType;
        this.followersCount = 0; // Изначально 0
        this.followingCount = 0; // Изначально 0
        this.attendedEventsCount = 0; // Изначально 0
    }

    public String getId() {
        return id;
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

    public void incrementAttendedEventsCount() { attendedEventsCount++; }
    public void decrementAttendedEventsCount() {
        if (attendedEventsCount > 0) attendedEventsCount--;
    }
}

