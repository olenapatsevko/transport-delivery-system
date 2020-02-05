package com.delivery.model.entity;

public class User {

    private final int id;
    private final String firstName;
    private final String secondName;
    private final String email;
    private final String password;
    private final String phone;
    private final Role role;

    public User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.role = builder.role;
        this.password = builder.password;

    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "[*******]" + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }



    public static class Builder {
        private int id;
        private String firstName;
        private String secondName;
        private String email;
        private String password;
        private String phone;
        private Role role;

        public User build() {
            return new User(this);
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }


    }

}
