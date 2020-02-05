package com.delivery.model.service;

import com.delivery.model.entity.User;

public interface UserService {

    User login(String email, String password) ;

    void register(User user);
}
