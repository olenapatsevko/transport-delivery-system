package com.delivery.model.db.impl;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.DataBaseConnectorTest;
import com.delivery.model.entity.Role;
import com.delivery.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    DataBaseConnector connector ;
    UserDao userDao;


    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        userDao = new UserDao(connector);
        DataBaseConnectorTest.initTestDb(connector);

    }

    @Test
    public void findUserByEmail(){
        assertEquals(14,userDao.findByEmail("dlecountd@exblog.jp").get().getId() );
    }

    @Test
    public void findUserById(){
        assertEquals("Morgen", userDao.findById(1).get().getFirstName());
    }

    @Test
    public void countUsers(){
        assertEquals(20, userDao.count());
    }

    @Test
    public void findUsersPerPage(){
        assertEquals(3 , userDao.findAll(3,3).size());
    }

    @Test
    public void saveNewUser(){
        userDao.save(User.builder().withEmail("asd").withFirstName("asd")
                .withId((int) (userDao.count()+1))
                .withPassword("sdfg")
                .withSecondName("sfgdf")
                .withPhone("fg")
                .withRole(Role.ADMIN).build());
        assertEquals(21, userDao.count());
    }

    @Test
    public void deleteuserById(){
        userDao.deleteById(12);
        assertEquals(19, userDao.count());
    }
}