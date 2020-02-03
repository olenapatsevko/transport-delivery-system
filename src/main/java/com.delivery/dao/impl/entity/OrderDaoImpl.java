package com.delivery.dao.impl.entity;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.OrderDao;
import com.delivery.dao.impl.entity.core.AbstractCrudDaoImpl;
import com.delivery.entity.Order;
import com.delivery.entity.enums.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends AbstractCrudDaoImpl<Order> implements OrderDao {
    private static final String FIND_BY_ID = "SELECT * FROM order WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM order WHERE id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) AS count FROM order ";
    private static final String FIND_ALL_LIMIT = "SELECT * FROM order LIMIT (?, ?)";
    private static final String SAVE_ENTITY = "INSERT INTO order (id, user, dispatch, destination, receiver, status, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ALL = "UPDATE order SET  user = ? , dispatch = ? , destination = ? , receiver = ? , status = ? , address = ?  WHERE id = ?";


    protected OrderDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL_LIMIT, SAVE_ENTITY, UPDATE_ALL);
    }


    @Override
    protected Order mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .withId(resultSet.getInt("id"))
                .withSender(new UserDaoImpl(this.connector).findById(resultSet.getInt("user")).get())
                .withDispatch(new PlaceDaoImpl(this.connector).findById(resultSet.getInt("dispatch")).get())
                .withDestination(new PlaceDaoImpl(this.connector).findById(resultSet.getInt("destination")).get())
                .withReceiver(new UserDaoImpl(this.connector).findById(resultSet.getInt("receiver")).get())
                .withOrderStatus(OrderStatus.valueOf(resultSet.getString("status")))
                .withAddress(resultSet.getString("address"))
                .build();
    }


    @Override
    protected void paramSet(Order entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setInt(2, entity.getSender().getId());
        preparedStatement.setInt(3, entity.getDispatch().getId());
        preparedStatement.setInt(4, entity.getDestination().getId());
        preparedStatement.setInt(5, entity.getReceiver().getId());
        preparedStatement.setString(6, entity.getOrderStatus().toString());
        preparedStatement.setString(7, entity.getAddress());

    }

    @Override
    protected void updateStatementSet(Order entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getSender().getId());
        preparedStatement.setInt(2, entity.getDispatch().getId());
        preparedStatement.setInt(3, entity.getDestination().getId());
        preparedStatement.setInt(4, entity.getReceiver().getId());
        preparedStatement.setString(5, entity.getOrderStatus().toString());
        preparedStatement.setString(6, entity.getAddress());
        preparedStatement.setInt(7, entity.getId());

    }

    @Override
    public List<Order> findAllOrdersForUser() {
        return null;
    }
}
