package com.delivery.model.db.impl;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.BillDao;
import com.delivery.model.db.impl.core.AbstractDaoImpl;
import com.delivery.model.entity.Bill;
import com.delivery.model.entity.User;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;
import com.delivery.model.entity.bill.Size;
import com.delivery.model.entity.bill.Weight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDaoImpl extends AbstractDaoImpl<Bill> implements BillDao {
    private static final String FIND_BY_ID = "SELECT * FROM bill WHERE id=? LIMIT 1";
    private static final String DELETE_BY_ID = "DELETE FROM bill WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM bill ";
    private static final String FIND_ALL_LIMIT = "SELECT * FROM bill LIMIT ?, ?";
    private static final String SAVE_ENTITY = "INSERT INTO bill (id, shipment, payment, delivery, size, weight, material) VALUES (?, ?, ?, ?, ?, ?, ? )";
    private static final String UPDATE_ALL = "UPDATE bill SET  shipment = ? , payment = ? , delivery = ? , size = ? , weight = ? , material = ? WHERE id = ?";

    public BillDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL_LIMIT, SAVE_ENTITY, UPDATE_ALL);
    }

    @Override
    protected Bill mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Bill.builder()
                .withId(resultSet.getInt("id"))
                .withShipment(new ShipmentDaoImpl(this.connector).findById(resultSet.getInt("shipment")).get())
                .withPayment(resultSet.getBoolean("payment"))
                .withDeliveryType(DeliveryType.valueOf(resultSet.getString("delivery")))
                .withSize(Size.valueOf(resultSet.getString("size")))
                .withWeight(Weight.valueOf(resultSet.getString("weight")))
                .withShipmentMaterial(Material.valueOf(resultSet.getString("material")))
                .build();
    }


    @Override
    protected void paramSet(Bill entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setInt(2, entity.getShipment().getId());
        preparedStatement.setBoolean(3, entity.isPayment());
        preparedStatement.setString(4, entity.getDeliveryType().toString());
        preparedStatement.setString(5, entity.getSize().toString());
        preparedStatement.setString(6, entity.getWeight().toString());
        preparedStatement.setString(7, entity.getMaterial().toString());
    }

    @Override
    protected void updateStatementSet(Bill entity, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setInt(1, entity.getShipment().getId());
        preparedStatement.setBoolean(2, entity.isPayment());
        preparedStatement.setString(3, entity.getDeliveryType().toString());
        preparedStatement.setString(4, entity.getSize().toString());
        preparedStatement.setString(5, entity.getWeight().toString());
        preparedStatement.setString(6, entity.getMaterial().toString());
        preparedStatement.setInt(7, entity.getId());
    }

    @Override
    public List<Bill> findAllBillsForUser(User user) {
        return null;
    }
}
