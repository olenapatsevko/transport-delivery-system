package com.delivery.model.db.impl;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.DataBaseConnectorTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillDaoImplTest {
    DataBaseConnector connector ;
    BillDaoImpl billDao;


    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        billDao = new BillDaoImpl(connector);
        DataBaseConnectorTest.initTestDb(connector);

    }

    @Test
    public void findOrderById(){
        assertEquals("TOWN", billDao.findById(1).get().getDeliveryType().toString());
    }

    @Test
    public void countUsers(){
        assertEquals(2, billDao.count());
    }

    @Test
    public void findUsersPerPage(){
        assertEquals(2 , billDao.findAll(1,3).size());
    }

    @Test
    public void deleteBillById(){
        billDao.deleteById(1);
        assertEquals(1, billDao.count());
    }

}