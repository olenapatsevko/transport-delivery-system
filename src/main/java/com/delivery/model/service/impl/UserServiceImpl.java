package com.delivery.model.service.impl;

import com.delivery.model.dao.impl.UserDaoImpl;
import com.delivery.model.domain.UserDomain;
import com.delivery.model.entity.User;
import com.delivery.model.exeption.LoginException;
import com.delivery.model.exeption.ValidationException;
import com.delivery.model.mapper.UserMapper;
import com.delivery.model.service.PasswordEncryption;
import com.delivery.model.service.UserService;
import com.delivery.model.service.validator.UserValidator;

import java.util.Optional;

public final class UserServiceImpl implements UserService {
    private UserDaoImpl userDaoImpl;
    private UserValidator userValidator;
    private UserMapper userMapper;
    private PasswordEncryption passwordEncryption;

    public UserServiceImpl(UserDaoImpl userDaoImpl, UserValidator userValidator, UserMapper userMapper, PasswordEncryption passwordEncryption) {
        this.userDaoImpl = userDaoImpl;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
        this.passwordEncryption = passwordEncryption;
    }

    @Override
    public User login(String email, String password) {
        if (userValidator.validate(email, "email")) {
            Optional<User> user = userDaoImpl.findByEmail(email);
            if (user.isPresent()) {
                if (user.get().getPassword().equals(passwordEncryption.encrypt(password))) {

                    return user.get();
                }
                throw new ValidationException("Invalid input");
            }
        }
        throw new LoginException("There no such user");
    }


    @Override
    public void register(UserDomain user) {
        if (userDaoImpl.findByEmail(user.getEmail()).isPresent()){
            throw new LoginException("user with the same email already exists");
        }
        userValidator.validate(userMapper.mapToEntity(user));
        userDaoImpl.save(userMapper.mapToEntity(user));
    }
}

