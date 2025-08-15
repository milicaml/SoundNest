package com.soundnest.model;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionUser {
    private User user;

    public SessionUser() {
        this.user = null;
    }

    public boolean isLogged() {
        return this.user != null;
    }

    public void login(User user) {
        if (isLogged()) logout();
        this.user = user;
    }

    public void logout() {
        this.user = null;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "user=" + user +
                '}';
    }
}
