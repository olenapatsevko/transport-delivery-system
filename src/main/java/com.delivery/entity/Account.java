package com.delivery.entity;

public class Account {

    int id;
    User user;

    public Account(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
