package com.delivery.dao.entity;


import com.delivery.dao.entity.core.CrudPageableDao;
import com.delivery.entity.User;

import java.util.Optional;

public interface UserDao extends CrudPageableDao<User> {

    Optional<User> findByEmail(String email);
}