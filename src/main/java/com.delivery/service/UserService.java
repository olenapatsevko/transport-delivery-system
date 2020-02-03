package com.delivery.service;

import com.delivery.entity.User;
import com.delivery.exeption.ValidationException;

public interface UserService {

    User login(String email, String password) throws ValidationException;

    void register(User user) throws ValidationException;
}
