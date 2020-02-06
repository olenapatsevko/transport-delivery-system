package com.delivery.model.db;


import com.delivery.model.db.CrudPageableDao;
import com.delivery.model.entity.User;

import java.util.Optional;

public interface UserDao extends CrudPageableDao<User> {

    Optional<User> findByEmail(String email);


}