package com.delivery.model.dao.impl.delivery.additional.param;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.impl.core.AbstractUnchangeableCrudDaoImpl;
import com.delivery.model.entity.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderStatusDaoImpl extends AbstractUnchangeableCrudDaoImpl<OrderStatus> {
    private static final String FIND_BY_ID = "SELECT * FROM status WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM status WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM status ";
    private static final String FIND_ALL = "SELECT * FROM status";
    private static final String FIND_BY_NAME = "SELECT * from status where name = ?";

    public OrderStatusDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }

    @Override
    public OrderStatus mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return OrderStatus.valueOf(resultset.getString("name"));
    }

    @Override
    public Optional<OrderStatus> findById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    public OrderStatus findByName(String name) {
        Optional<OrderStatus> orderStatus = findByParam(name, FIND_BY_NAME, STRING_PARAM_SETTER);
        return orderStatus.orElse(null);
    }

}
