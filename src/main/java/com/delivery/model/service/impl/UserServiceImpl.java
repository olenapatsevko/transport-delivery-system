package com.delivery.model.service.impl;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.impl.UserDaoImpl;
import com.delivery.model.domain.UserDomain;
import com.delivery.model.entity.User;
import com.delivery.model.exeption.LoginException;
import com.delivery.model.mapper.UserMapper;
import com.delivery.model.service.PasswordEncryption;
import com.delivery.model.service.UserService;
import com.delivery.model.service.validator.UserValidator;

import java.util.Optional;

public final class UserServiceImpl implements UserService {
    private UserDaoImpl userDao = new UserDaoImpl(new DataBaseConnector());
    private UserValidator userValidator = new UserValidator();
    private UserMapper userMapper = new UserMapper(new PasswordEncryption());

    @Override
    public User login(String email, String password) {
        if (userValidator.validate(email, "email")) {
            Optional<User> user = userDao.findByEmail(email);
            if (user.isPresent()) {
             //   if (user.get().getPassword().equals(userMapper.mapToEntity(UserDomain.builder().withPassword(password).build()).getPassword())) {
                    return user.get();
             //   }
            }
        }
        return null;
        //throw new LoginException("Data are incorrect or there is no such user");
    }


    @Override
    public void register(UserDomain user) {
        userValidator.validate(userMapper.mapToEntity(user));
        userDao.save(userMapper.mapToEntity(user));
    }
}

