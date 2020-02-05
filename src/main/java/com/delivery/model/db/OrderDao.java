package com.delivery.model.db;

import com.delivery.model.db.CrudPageableDao;
import com.delivery.model.entity.Order;

import java.util.List;

public interface OrderDao extends CrudPageableDao<Order> {

    List<Order> findAllOrdersForUser();
}
