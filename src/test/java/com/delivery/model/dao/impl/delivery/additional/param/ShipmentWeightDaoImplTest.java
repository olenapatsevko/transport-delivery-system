package com.delivery.model.dao.impl.delivery.additional.param;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.DataBaseConnectorTest;
import com.delivery.model.entity.bill.Weight;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShipmentWeightDaoImplTest {
    DataBaseConnector connector ;
    WeightDaoImpl shipmentWeightDao;

    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        shipmentWeightDao = new WeightDaoImpl(connector);
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