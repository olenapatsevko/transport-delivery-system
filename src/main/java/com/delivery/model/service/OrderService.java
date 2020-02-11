package com.delivery.model.service;

import com.delivery.model.db.impl.*;
import com.delivery.model.domain.PlaceDomain;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.mapper.ShipmentMapper;
import com.delivery.model.service.impl.DeliveryCalculationImpl;
import com.delivery.model.service.validator.UserValidator;

public interface OrderService {
    void makeOrder(UserDao userDao, BillDaoImpl billDaoImp,
                   ShipmentDaoImpl shipmentDao, OrderDaoImpl orderDao, PlaceDaoImpl placeDao,
                   DeliveryCalculationImpl deliveryCalculationImpl, ShipmentMapper shipmentMapper,
                   UserValidator userValidator);


}
