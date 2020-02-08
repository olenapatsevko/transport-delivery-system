package com.delivery.model.mapper;

import com.delivery.model.entity.User;
import com.delivery.model.domain.UserDomain;
import com.delivery.model.service.PasswordEncryption;

public class UserMapper implements Mapper<User, UserDomain> {
    private final PasswordEncryption passwordEncryption;

    public UserMapper(PasswordEncryption passwordEncryption) {
        this.passwordEncryption = passwordEncryption;
    }

    @Override
    public UserDomain mapToDomain(User user) {
        return UserDomain.builder()
                .withEmail(user.getEmail())
                .withRole(user.getRole())
                .withFirstName(user.getFirstName())
                .withSecondName(user.getSecondName())
                .withPhone(user.getPhone())
                .build();
    }

    @Override
    public User mapToEntity(UserDomain user) {
        return User.builder()
                .withEmail(user.getEmail())
                .withRole(user.getRole())
                .withPassword(passwordEncryption.encrypt(user.getPassword()))
                .withFirstName(user.getFirstName())
                .withSecondName(user.getSecondName())
                .withPhone(user.getPhone())
                .withId(0)
                .build();
    }
}
