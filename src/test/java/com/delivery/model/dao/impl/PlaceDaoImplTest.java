package com.delivery.model.dao.impl;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.DataBaseConnectorTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlaceDaoImplTest {

    DataBaseConnector connector;
    PlaceDaoImpl placeDao;

    @Before
    public void initDb() {
        connector = new DataBaseConnector("h2");
        placeDao = new PlaceDaoImpl(connector);
        DataBaseConnectorTest.initTestDb(connector);
    }

    @Test
    public void countAllCities() {
        assertEquals(4, placeDao.count());
    }

    @Test
    public void findByIdNotNull() {
        assertEquals("Lviv", placeDao.findById(1).get().getCity());
    }

    @Test
    public void findByIdNull() {
        assertTrue(!placeDao.findById(23).isPresent());
    }

    @Test
    public void deleteByIdNotNull(){
        placeDao.deleteById(1);
        assertEquals(3, placeDao.count());
    }
    @Test
    public void deleteByIdNull(){
        placeDao.deleteById(66);
        assertEquals(4, placeDao.count());
    }

    @Test
    public void findAll(){
        assertEquals(4,placeDao.findAll().size());
    }
}