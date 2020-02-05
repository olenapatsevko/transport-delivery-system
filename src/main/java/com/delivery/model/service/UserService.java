package com.delivery.model.service;

import com.delivery.model.entity.User;
import com.delivery.model.exeption.ValidationException;

public interface UserService {

    User login(String email, String password) ;

    void register(User user) throws ValidationException;
}
