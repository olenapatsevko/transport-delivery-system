package com.delivery.model.entity;

public enum Role {
    USER(true, "user"), ADMIN(false, "admin");

    private boolean isUser;
    private String role;

    public boolean getVal() {
        return this.isUser;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        return role;

    }

    Role(boolean b, String role) {
        this.isUser = b;
        this.role = role;
    }

}