package com.delivery.model.domain;

import com.delivery.model.entity.Role;

import java.util.Objects;

public class UserDomain {


    private  String firstName;
    private  String secondName;
    private  String email;
    private  String password;
    private  String phone;
    private  Role role;

    public UserDomain(Builder builder) {

        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.email = builder.email;
        this.phone = builder.phone;
        this.role = builder.role;
        this.password = builder.password;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDomain user = (UserDomain) o;
        return
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash( firstName, secondName, email, password, phone, role);
    }

    public static Builder builder() {
        return new Builder();
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
                " firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "[*******]" + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }



    public static class Builder {

        private String firstName;
        private String secondName;
        private String email;
        private String password;
        private String phone;
        private Role role;

        public UserDomain build() {
            return new UserDomain(this);
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
