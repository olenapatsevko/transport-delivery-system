package com.delivery.model.service.impl;

import com.delivery.model.dao.impl.PlaceDaoImpl;
import com.delivery.model.mapper.PlaceMapper;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryCalculationImplTest {

    @Mock
    private PlaceMapper placeMapper;
    @Mock
    private PlaceDaoImpl placeDao;
    @InjectMocks
    private DeliveryCalculationImpl deliveryCalculation;

}