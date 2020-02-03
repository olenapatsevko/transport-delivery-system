package com.delivery.dao.entity;

import com.delivery.dao.entity.core.CrudPageableDao;
import com.delivery.entity.Order;

import java.util.List;

public interface OrderDao extends CrudPageableDao<Order> {

    List<Order> findAllOrdersForUser();
}
