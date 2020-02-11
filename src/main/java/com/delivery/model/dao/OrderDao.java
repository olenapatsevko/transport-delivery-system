package com.delivery.model.dao;

import com.delivery.model.entity.Order;

import java.util.List;

public interface OrderDao extends CrudPageableDao<Order> {

    List<Order> findAllOrdersForUser();
}
