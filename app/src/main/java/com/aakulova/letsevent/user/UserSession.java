package com.aakulova.letsevent.user;

public class UserSession {
    private static UserSession instance;
    private User currentUser;

//    private UserSession() {}
    private UserSession() {
        // Инициализация пользователя
        currentUser = new User("1", "User", "user@example.com", "", "business");
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

