package com.delivery.model.dao.impl.delivery.additional.param;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.DataBaseConnectorTest;
import com.delivery.model.entity.bill.Material;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaterialDaoImplTest {
    DataBaseConnector connector ;
    MaterialDaoImpl shipmentMaterialDao;


    @Before
  public void initDb(){
       connector = new DataBaseConnector("h2");
       shipmentMaterialDao = new MaterialDaoImpl(connector);
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