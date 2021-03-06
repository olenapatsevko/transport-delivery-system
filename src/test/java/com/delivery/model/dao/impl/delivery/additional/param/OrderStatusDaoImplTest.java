package com.delivery.model.dao.impl.delivery.additional.param;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.DataBaseConnectorTest;
import com.delivery.model.entity.OrderStatus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderStatusDaoImplTest {
    DataBaseConnector connector ;
    OrderStatusDaoImpl orderStatusCrudDao;

    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        orderStatusCrudDao = new OrderStatusDaoImpl(connector);
        DataBaseConnectorTest.initTestDb(connector);

    }

    @Test
    public void findAllSize(){
        assertEquals(3, orderStatusCrudDao.findAll().size());
    }

    @Test
    public void countAll(){
        assertEquals(3, orderStatusCrudDao.count());

    }


    @Test
    public void findByNameNull(){
        assertNull(orderStatusCrudDao.findByName("TEST"));
    }

    @Test
    public void findByNameNotNull(){
        assertEquals(OrderStatus.ACTIVE , orderStatusCrudDao.findByName("ACTIVE"));
    }




    @Test(expected = UnsupportedOperationException.class)
    public void findById(){
        orderStatusCrudDao.findById(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void deleteById(){
        orderStatusCrudDao.deleteById(1);
    }
}