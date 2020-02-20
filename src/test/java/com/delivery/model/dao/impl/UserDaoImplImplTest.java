package com.delivery.model.dao.impl;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.DataBaseConnectorTest;
import com.delivery.model.entity.Role;
import com.delivery.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImplImplTest {
    DataBaseConnector connector ;
    UserDaoImpl userDaoImpl;


    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        userDaoImpl = new UserDaoImpl(connector);
        DataBaseConnectorTest.initTestDb(connector);

    }

    @Test
    public void findUserByEmail(){
        assertEquals(14, userDaoImpl.findByEmail("dlecountd@exblog.jp").get().getId() );
    }

    @Test
    public void findUserById(){
        assertEquals("Morgen", userDaoImpl.findById(1).get().getFirstName());
    }

    @Test
    public void countUsers(){
        assertEquals(20, userDaoImpl.count());
    }

    @Test
    public void findUsersPerPage(){
        assertEquals(3 , userDaoImpl.findAll(3,3).size());
    }

    @Test
    public void saveNewUser(){
        userDaoImpl.save(User.builder().withEmail("asd").withFirstName("asd")
                .withId((int) (userDaoImpl.count()+1))
                .withPassword("sdfg")
                .withSecondName("sfgdf")
                .withPhone("fg")
                .withRole(Role.ADMIN).build());
        assertEquals(21, userDaoImpl.count());
    }

    @Test
    public void deleteuserById(){
        userDaoImpl.deleteById(12);
        assertEquals(19, userDaoImpl.count());
    }
}