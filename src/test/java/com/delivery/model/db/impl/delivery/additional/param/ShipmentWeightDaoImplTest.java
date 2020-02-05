package com.delivery.model.db.impl.delivery.additional.param;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.DataBaseConnectorTest;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Weight;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShipmentWeightDaoImplTest {
    DataBaseConnector connector ;
    ShipmentWeightDaoImpl shipmentWeightDao;

    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        shipmentWeightDao = new ShipmentWeightDaoImpl(connector);
        DataBaseConnectorTest.initTestDb(connector);
    }
    @Test
    public void findAllTestSize(){
        assertEquals( 4, shipmentWeightDao.findAll().size());
    }

    @Test
    public void countAll(){
        assertEquals(4, shipmentWeightDao.count());
    }

    @Test
    public void findByNameNull(){
        assertNull(shipmentWeightDao.findByName("TEST"));
    }

    @Test
    public void findByNameNotNull(){
        assertEquals(Weight.HEAVY, shipmentWeightDao.findByName("HEAVY"));
    }


    @Test(expected = UnsupportedOperationException.class)
    public void findById(){
        shipmentWeightDao.findById(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void deleteById(){
        shipmentWeightDao.deleteById(1);
    }

}