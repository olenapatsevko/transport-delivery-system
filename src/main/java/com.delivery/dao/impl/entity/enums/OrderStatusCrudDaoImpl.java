package com.delivery.dao.impl.entity.enums;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.enums.OrderStatusDao;
import com.delivery.dao.impl.entity.core.AbstractEnumCrudDaoImpl;
import com.delivery.entity.enums.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusCrudDaoImpl extends AbstractEnumCrudDaoImpl<OrderStatus> implements OrderStatusDao {
    private static final String FIND_BY_ID = "SELECT * FROM status WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM status WHERE id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) AS count FROM status ";
    private static final String FIND_ALL = "SELECT * FROM status";

    public OrderStatusCrudDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }

    @Override
    public OrderStatus mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return OrderStatus.valueOf(resultset.getString("name"));
    }


}
