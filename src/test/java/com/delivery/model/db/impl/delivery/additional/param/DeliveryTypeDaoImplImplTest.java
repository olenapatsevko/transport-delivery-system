package com.delivery.model.db.impl.delivery.additional.param;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.DataBaseConnectorTest;
import com.delivery.model.entity.bill.DeliveryType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeliveryTypeDaoImplImplTest {
    DataBaseConnector connector ;
    DeliveryTypeDaoImplImpl deliveryType;

    @Before
    public void initDb(){
        connector = new DataBaseConnector("h2");
        deliveryType = new DeliveryTypeDaoImplImpl(connector);
        DataBaseConnectorTest.initTestDb(connector);
    }

    @Test
    public void findAllSize(){
        assertEquals(3, deliveryType.findAll().size());
    }

    @Test
    public void countAll(){
        assertEquals(3, deliveryType.count());

    }

    @Test(expected = UnsupportedOperationException.class)
    public void findById(){
        deliveryType.findById(1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void deleteById(){
        deliveryType.deleteById(1);
    }

    @Test
    public void findByNameNull(){
        assertNull(deliveryType.findByName("TEST"));
    }

    @Test
    public void findByNameNotNull(){
        assertEquals(DeliveryType.TOWN , deliveryType.findByName("TOWN"));
    }

}