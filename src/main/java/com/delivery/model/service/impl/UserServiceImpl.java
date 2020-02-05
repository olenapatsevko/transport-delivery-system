package com.delivery.model.service.impl;


import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.impl.UserDaoImpl;
import com.delivery.model.entity.User;
import com.delivery.model.exeption.LoginException;
import com.delivery.model.service.PasswordEncryption;
import com.delivery.model.service.UserService;
import com.delivery.model.service.validator.UserValidator;

import java.util.Optional;

public final class UserServiceImpl implements UserService {
    private static final UserDaoImpl userDao = new UserDaoImpl(new DataBaseConnector());
    private static final UserValidator userValidator = new UserValidator();

    @Override
    public User login(String email, String password) {
        if (userValidator.validate(email, "email")) {
            Optional<User> user = userDao.loginUser(email, PasswordEncryption.encrypt(password));  ///encrypt
            if (user.isPresent()) {
                return user.get();
            }
        }
        throw new LoginException("Data are incorrect or there is no such user");
    }


    @Override
    public  void register(User user) {
        userValidator.validate(user);
        userDao.save(User.builder()
                    .withRole(user.getRole())
                    .withPhone(user.getPhone())
                    .withSecondName(user.getSecondName())
                    .withPassword(PasswordEncryption.encrypt(user.getPassword()))
                    .withId(user.getId())
                    .withFirstName(user.getFirstName())
                    .withEmail(user.getEmail())
                    .build());
        }

    }

