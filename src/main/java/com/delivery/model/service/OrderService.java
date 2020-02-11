package com.delivery.model.service;

import com.delivery.model.dao.impl.*;
import com.delivery.model.mapper.ShipmentMapper;
import com.delivery.model.service.impl.DeliveryCalculationImpl;
import com.delivery.model.service.validator.UserValidator;

public interface OrderService {
    void makeOrder(UserDao userDao, BillDaoImpl billDaoImp,
                   ShipmentDaoImpl shipmentDao, OrderDaoImpl orderDao, PlaceDaoImpl placeDao,
                   DeliveryCalculationImpl deliveryCalculationImpl, ShipmentMapper shipmentMapper,
                   UserValidator userValidator);


}
