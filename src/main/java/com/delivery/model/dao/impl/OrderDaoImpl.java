package com.delivery.model.dao.impl;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.OrderDao;
import com.delivery.model.dao.impl.core.AbstractDaoImpl;
import com.delivery.model.entity.Order;
import com.delivery.model.entity.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDao {
    private static final String FIND_BY_ID = "SELECT * FROM orders WHERE id=? LIMIT 1";
    private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM orders ";
    private static final String FIND_ALL_LIMIT = "SELECT * FROM orders LIMIT ?, ?";
    private static final String SAVE_ENTITY = "INSERT INTO  orders (id, user, dispatch, destination, address, receive_date, status, shipment) VALUES (?, ?, ?,?,?,? , ?, ?)";
    private static final String UPDATE_ALL = "UPDATE orders SET  user = ? , dispatch = ?, destination = ? ,  address = ?,receive_date = ? , status = ? ,  shipment = ?  WHERE id = ?";


    public OrderDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL_LIMIT, SAVE_ENTITY, UPDATE_ALL);
    }


    @Override
    protected Order mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .withId(resultSet.getInt("id"))
                .withSender(new UserDaoImpl(this.connector).findById(resultSet.getInt("user")).get())
                .withDestination(new PlaceDaoImpl(this.connector).findById(resultSet.getInt("destination")).get())
                .withDispatch(new PlaceDaoImpl(this.connector).findById(resultSet.getInt("dispatch")).get())
                .withOrderStatus(OrderStatus.valueOf(resultSet.getString("status")))
                .withAddress(resultSet.getString("address"))
                .withDeliveryDate(resultSet.getObject("receive_date", LocalDateTime.class))
                .withShipment(new ShipmentDaoImpl(this.connector).findById(resultSet.getInt("shipment")).get())
                .build();
    }


    @Override
    protected void paramSet(Order entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setInt(2, entity.getSender().getId());
        preparedStatement.setInt(3, entity.getDispatch().getId());
        preparedStatement.setInt(4, entity.getDestination().getId());
        preparedStatement.setString(5, entity.getAddress());
        preparedStatement.setObject(6, entity.getDeliveryDate() );
        preparedStatement.setString(7, entity.getOrderStatus().toString());
        preparedStatement.setInt(8, entity.getShipment().getId());

    }

    @Override
    protected void updateStatementSet(Order entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getSender().getId());
        preparedStatement.setInt(2, entity.getDispatch().getId());
        preparedStatement.setInt(3, entity.getDestination().getId());
        preparedStatement.setString(4, entity.getAddress());
        preparedStatement.setObject(5, entity.getDeliveryDate() );
        preparedStatement.setString(6, entity.getOrderStatus().toString());
        preparedStatement.setInt(7, entity.getShipment().getId());
        preparedStatement.setInt(8, entity.getId());

    }

    @Override
    public List<Order> findAllOrdersForUser() {
        return null;
    }
}
