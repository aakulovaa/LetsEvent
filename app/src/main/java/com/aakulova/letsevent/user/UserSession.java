package com.aakulova.letsevent.user;

import com.aakulova.letsevent.models.User;

public class UserSession {
    private static UserSession instance;
    private User currentUser;

    //    private UserSession() {}
    private UserSession() {
        // Инициализация пользователя
        currentUser = new User(User.generateUniqueId(), User.generateRandomUsername(), "user@example.com", "", "business");
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}

