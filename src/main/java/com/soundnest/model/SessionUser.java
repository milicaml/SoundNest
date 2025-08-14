package com.soundnest.model;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionUser {
    private User user;
    private String role;

    public SessionUser() {
        this(null, null);
    }

    public SessionUser(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public boolean isAdmin(){
        return this.role.equals("Admin");
    }

    public boolean isLogged() {
        return this.user != null;
    }

    public void login(User user, String role) {
        if (isLogged()) logout();
        this.user = user;
        if (!role.equals("Admin") && !role.equals("User")) {
            throw new IllegalArgumentException("Invalid role");
        }
        this.role = role;
    }

    public void logout() {
        this.user = null;
        this.role = null;
    }

    public User getUser() {
        return user;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "user=" + user +
                ", role='" + role + '\'' +
                '}';
    }
}
