package com.delivery.model.mapper;

import com.delivery.model.domain.OrderDomain;
import com.delivery.model.entity.Order;

public class OrderMapper implements Mapper<Order, OrderDomain> {


    @Override
    public OrderDomain mapToDomain(Order order) {
        return OrderDomain.builder()
                .withAddress(order.getAddress())
                .withDestination(order.getDestination())
                .withId(order.getId())
                .withOrderStatus(order.getOrderStatus())
                .withSender(order.getSender())
                .build();
    }

    @Override
    public Order mapToEntity(OrderDomain order) {
        return Order.builder()
                .withAddress(order.getAddress())
                .withDestination(order.getDestination())
                .withId(order.getId())
                .withOrderStatus(order.getOrderStatus())
                .withSender(order.getSender())
                .build();
    }
}
