package com.delivery.model.db.impl;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.CrudPageableDao;
import com.delivery.model.db.impl.core.AbstractDaoImpl;
import com.delivery.model.entity.Shipment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentDaoImpl extends AbstractDaoImpl<Shipment> implements CrudPageableDao<Shipment> {
    private static final String FIND_BY_ID = "SELECT * FROM shipment WHERE id=? LIMIT 1";
    private static final String DELETE_BY_ID = "DELETE FROM shipment WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM shipment ";
    private static final String FIND_ALL_LIMIT = "SELECT * FROM shipment LIMIT ?, ?";
    private static final String SAVE_ENTITY = "INSERT INTO shipment (id, weight, height, width, length) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_ALL = "UPDATE shipment SET weight = ? , height = ? , width = ? , length = ? WHERE id = ?";

    public ShipmentDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL_LIMIT, SAVE_ENTITY, UPDATE_ALL);
    }

    @Override
    protected Shipment mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Shipment.builder()
                .withId(resultSet.getInt("id"))
                .withWeight(resultSet.getFloat("weight"))
                .withHeight(resultSet.getFloat("height"))
                .withWidth(resultSet.getFloat("width"))
                .withLength(resultSet.getFloat("length"))
                .build();
    }

    @Override
    protected void updateStatementSet(Shipment entity, PreparedStatement statement) throws SQLException {
                statement.setFloat(1, entity.getWeight());
                statement.setFloat(2,entity.getHeight());
                statement.setFloat(3,entity.getWidth());
                statement.setFloat(4,entity.getLength());
                statement.setInt(5, entity.getId());
    }

    @Override
    protected void paramSet(Shipment entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setFloat(2, entity.getWeight());
        preparedStatement.setFloat(3, entity.getHeight());
        preparedStatement.setFloat(4, entity.getWidth());
        preparedStatement.setFloat(5, entity.getLength());

    }
}
