package com.delivery.model.db.impl.delivery.additional.param;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.DataBaseConnectorTest;
import com.delivery.model.entity.bill.Size;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShipmentSizeDaoImplTest {
    DataBaseConnector connector ;
    SizeDaoImpl shipmentSizeDao;


    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        shipmentSizeDao = new SizeDaoImpl(connector);
        DataBaseConnectorTest.initTestDb(connector);

    }
    @Test
    public void findAllTestSize(){
        assertEquals(shipmentSizeDao.findAll().size(), 3);
    }

    @Test
    public void countAll(){
        assertEquals(3, shipmentSizeDao.count());
    }

    @Test
    public void findByNameNull(){
        assertNull(shipmentSizeDao.findByName("TEST"));
    }

    @Test
    public void findByNameNotNull(){
        assertEquals(Size.BIG, shipmentSizeDao.findByName("BIG"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findById(){
        shipmentSizeDao.findById(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void deleteById(){
        shipmentSizeDao.deleteById(1);
    }

}