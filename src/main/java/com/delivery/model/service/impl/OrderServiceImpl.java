package com.delivery.model.service.impl;

import com.delivery.model.dao.impl.*;
import com.delivery.model.domain.PlaceDomain;
import com.delivery.model.domain.ShipmentDomain;
import com.delivery.model.domain.UserDomain;
import com.delivery.model.entity.Bill;
import com.delivery.model.entity.Order;
import com.delivery.model.entity.OrderStatus;
import com.delivery.model.entity.Shipment;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.exeption.ValidationException;
import com.delivery.model.mapper.ShipmentMapper;
import com.delivery.model.service.DeliveryCalculation;
import com.delivery.model.service.validator.UserValidator;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderServiceImpl {
    private final UserDaoImpl userDaoImpl;
    private final BillDaoImpl billDaoImp;
    private final ShipmentDaoImpl shipmentDao;
    private final OrderDaoImpl orderDao;
    private final PlaceDaoImpl placeDao;
    private final DeliveryCalculation deliveryCalculationImpl;
    private final ShipmentMapper shipmentMapper;
    private final UserValidator userValidator;

    public OrderServiceImpl(UserDaoImpl userDaoImpl, BillDaoImpl billDaoImp,
                            ShipmentDaoImpl shipmentDao, OrderDaoImpl orderDao, PlaceDaoImpl placeDao,
                            DeliveryCalculation deliveryCalculationImpl, ShipmentMapper shipmentMapper,
                            UserValidator userValidator) {
        this.userDaoImpl = userDaoImpl;
        this.billDaoImp = billDaoImp;
        this.shipmentDao = shipmentDao;
        this.orderDao = orderDao;
        this.placeDao = placeDao;
        this.deliveryCalculationImpl = deliveryCalculationImpl;
        this.shipmentMapper = shipmentMapper;
        this.userValidator = userValidator;
    }

    public void makeOrder(PlaceDomain dispatch, PlaceDomain destination,
                          ShipmentDomain shipmentDomain, Material material, UserDomain userDomain, String address) {
        if (!userValidator.validate(address, "address")) {
            throw new ValidationException("address is incorrect");
        }
        Shipment shipment = createShipment(shipmentDomain);
        Order order = createOrder(shipment, dispatch, destination, userDomain, address);
        Bill bill = createBill(order, material, dispatch, destination, getTotalPrice(dispatch, shipmentDomain, material));
        shipmentDao.save(shipment);
        orderDao.save(order);
        billDaoImp.save(bill);

    }

    private float getTotalPrice(PlaceDomain dispatch, ShipmentDomain shipmentDomain, Material material) {
        return deliveryCalculationImpl.calculateDelivery(dispatch, dispatch, material , shipmentDomain);
    }

    private Shipment createShipment(ShipmentDomain shipmentDomain) {
        return Shipment.builder()
                .withId((int) new Date().getTime())
                .withWidth(shipmentDomain.getWidth())
                .withLength(shipmentDomain.getLength())
                .withHeight(shipmentDomain.getHeight())
                .withWeight(shipmentDomain.getWeight())
                .build();
    }

    private Order createOrder(Shipment shipment, PlaceDomain dispatch,
                              PlaceDomain destination, UserDomain userDomain, String address) {
        return Order.builder()
                .withShipment(shipment)
                .withId((int) new Date().getTime())
                .withSender(userDaoImpl.findByEmail(userDomain.getEmail()).get())
                .withDispatch(placeDao.findByName(dispatch.getCity()).get())
                .withDestination(placeDao.findByName(destination.getCity()).get())
                .withDeliveryDate(countDeliveryDate(deliveryCalculationImpl.deliveryTypeDefiner(dispatch, destination)))
                .withOrderStatus(OrderStatus.NEW)
                .withAddress(address)
                .build();

    }

    private Bill createBill(Order order, Material material, PlaceDomain dispatch, PlaceDomain destination, float totalPrice) {
        return Bill.builder()
                .withId((int) new Date().getTime())
                .withSize(deliveryCalculationImpl.sizeTypeDefiner(shipmentMapper.mapToDomain(order.getShipment())))
                .withPayment(false)
                .withShipmentMaterial(material)
                .withDeliveryType(deliveryCalculationImpl.deliveryTypeDefiner(dispatch, destination))
                .withWeight(deliveryCalculationImpl.weightTypeDefiner(order.getShipment().getWeight()))
                .withOrder(order)
                .withTotalValue(Math.round(totalPrice))
                .build();
    }

    private LocalDateTime countDeliveryDate(DeliveryType deliveryType) {

        LocalDateTime deliveryDate = LocalDateTime.now();
        switch (deliveryDate.getDayOfWeek()) {
            case FRIDAY:
                deliveryDate = deliveryDate.plusDays(3);
                break;
            case SATURDAY:
                deliveryDate = deliveryDate.plusDays(2);
                break;
            case SUNDAY:
                deliveryDate = deliveryDate.plusDays(1);
                break;
        }

        switch (deliveryType) {
            case TOWN:
                deliveryDate = deliveryDate.plusDays(1);
                break;
            case REGION:
                deliveryDate = deliveryDate.plusDays(3);
                break;
            case COUNTRY:
                deliveryDate = deliveryDate.plusDays(5);
                break;
            default:
                deliveryDate = deliveryDate.plusDays(1);

        }

        return deliveryDate;
    }
}
