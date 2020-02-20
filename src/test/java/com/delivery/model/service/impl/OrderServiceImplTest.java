package com.delivery.model.service.impl;

import com.delivery.model.dao.impl.*;
import com.delivery.model.mapper.ShipmentMapper;
import com.delivery.model.service.DeliveryCalculation;
import com.delivery.model.service.validator.UserValidator;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private UserDaoImpl userDaoImpl;
    @Mock
    private BillDaoImpl billDaoImp;
    @Mock
    private ShipmentDaoImpl shipmentDao;
    @Mock
    private OrderDaoImpl orderDao;
    @Mock
    private PlaceDaoImpl placeDao;
    @Mock
    private DeliveryCalculation deliveryCalculationImpl;
    @Mock
    private ShipmentMapper shipmentMapper;
    @Mock
    private UserValidator userValidator;

    @InjectMocks
    OrderServiceImpl orderService;
}