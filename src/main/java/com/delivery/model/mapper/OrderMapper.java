package com.delivery.model.mapper;

import com.delivery.controller.injector.ApplicationInjector;
import com.delivery.model.domain.OrderDomain;
import com.delivery.model.entity.Order;

public class OrderMapper implements Mapper<Order, OrderDomain> {


    @Override
    public OrderDomain mapToDomain(Order order) {
        return OrderDomain.builder()
                .withAddress(order.getAddress())
                .withDestination(new PlaceMapper().mapToDomain(order.getDestination()))
                .withId(order.getId())
                .withOrderStatus(order.getOrderStatus())
                .withSender(new UserMapper(ApplicationInjector.getPasswordEncryption()).mapToDomain(order.getSender()))
                .withLocalDateTime(order.getDeliveryDate())
                .build();
    }

    @Override
    public Order mapToEntity(OrderDomain order) {
        return Order.builder()
                .withAddress(order.getAddress())
                .withDestination(new PlaceMapper().mapToEntity(order.getDestination()))
                .withId(order.getId())
                .withOrderStatus(order.getOrderStatus())
                .withDeliveryDate(order.getLocalDateTime())
                .withSender(ApplicationInjector.getUserMapper().mapToEntity(order.getSender()))
                .build();
    }
}
