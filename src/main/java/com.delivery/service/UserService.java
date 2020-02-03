package com.delivery.service;

import com.delivery.entity.User;
import com.delivery.exeption.ValidationException;

public interface UserService {

    User login(String email, String password) ;

    void register(User user) throws ValidationException;
}
