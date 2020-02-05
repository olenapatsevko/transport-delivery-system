package com.delivery.model.db.impl.delivery.additional.param;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.DataBaseConnectorTest;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShipmentMaterialDaoImplTest {
    DataBaseConnector connector ;
    ShipmentMaterialDaoImpl shipmentMaterialDao;


    @Before
  public void initDb(){
       connector = new DataBaseConnector("h2");
       shipmentMaterialDao = new ShipmentMaterialDaoImpl(connector);
       DataBaseConnectorTest.initTestDb(connector);

    }

    @Test
    public void findAllTestSize(){
        assertEquals(shipmentMaterialDao.findAll().size(), 2);
    }

    @Test
    public void countAll(){
        assertEquals(2, shipmentMaterialDao.count());
    }

    @Test
    public void findByNameNull(){
        assertNull(shipmentMaterialDao.findByName("TEST"));
    }

    @Test
    public void findByNameNotNull(){
        assertEquals(Material.GLASS , shipmentMaterialDao.findByName("GLASS"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findById(){
        shipmentMaterialDao.findById(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void deleteById(){
        shipmentMaterialDao.deleteById(1);
    }


}