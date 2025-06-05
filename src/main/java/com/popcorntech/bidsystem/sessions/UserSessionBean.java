package com.popcorntech.bidsystem.sessions;

import com.popcorntech.bidsystem.entities.User;
import jakarta.ejb.Stateful;

import java.io.Serializable;

@Stateful
public class UserSessionBean implements Serializable {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void logout() {
        this.user = null;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

}
