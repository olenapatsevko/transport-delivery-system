package com.delivery.service.impl;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.impl.entity.UserDaoImpl;
import com.delivery.entity.User;
import com.delivery.exeption.LoginException;
import com.delivery.exeption.ValidationException;
import com.delivery.service.PasswordEncryption;
import com.delivery.service.UserService;
import com.delivery.service.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl(new DataBaseConnector("delivery_schema_properties"));
    UserValidator userValidator = new UserValidator();

    @Override
    public User login(String email, String password) throws ValidationException {
        if (userValidator.validate(email ,"email")){
            Optional<User> user =userDao.loginUser(email , PasswordEncryption.encrypt(password));
            if (user.isPresent()){
                return user.get();
            }
        }
        throw  new LoginException();
    }

    @Override
    public void register(User user) {
        userDao.update(user);
    }
}
