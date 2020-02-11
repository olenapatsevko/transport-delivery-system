package com.delivery.model.dao.impl;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.DataBaseConnectorTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderDaoImplTest {


    DataBaseConnector connector ;
    OrderDaoImpl orderDao;


    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        orderDao = new OrderDaoImpl(connector);
        DataBaseConnectorTest.initTestDb(connector);

    }

    @Test
    public void findOrderById(){
        assertEquals("lime", orderDao.findById(1).get().getAddress());
    }

    @Test
    public void countUsers(){
        assertEquals(2, orderDao.count());
    }

    @Test
    public void findUsersPerPage(){
        assertEquals(2 , orderDao.findAll(1,3).size());
    }

    @Test
    public void deleteOrderById(){
        orderDao.deleteById(1);
        assertEquals(1, orderDao.count());
    }


}