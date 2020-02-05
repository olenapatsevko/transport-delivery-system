package com.delivery.model.db.impl;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.DataBaseConnectorTest;
import com.delivery.model.entity.Shipment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShipmentDaoImplTest {

    DataBaseConnector connector ;
    ShipmentDaoImpl shipmentDao;


    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        shipmentDao = new ShipmentDaoImpl(connector);
        DataBaseConnectorTest.initTestDb(connector);

    }

    @Test
    public void findAll(){
        assertEquals(2, shipmentDao.findAll(1,3).size());
    }

    @Test
    public void findShipmentById(){
        assertEquals("mwealleans0@godaddy.com",
                shipmentDao.findById(1).get().getOrder().getSender().getEmail());
    }

    @Test
    public void countOrders(){
        assertEquals(2, shipmentDao.count());
    }


    @Test
    public void deleteShipmentById(){
        shipmentDao.deleteById(1);
        assertEquals(1, shipmentDao.count());
    }

    @Test
    public void updateShipment(){
        shipmentDao.update(Shipment.builder()
                .withId(1)
                .withHeight(2.0f)
                .withLength(234f)
                .withWeight(34f)
                .withWidth(23f)
                .withOrder(shipmentDao
                        .findById(1)
                        .get()
                        .getOrder())
                .build());
        assertEquals(34f , shipmentDao.findById(1).get().getWeight(), 0.0);
    }

}