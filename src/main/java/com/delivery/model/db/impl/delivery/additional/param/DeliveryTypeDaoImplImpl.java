package com.delivery.model.db.impl.delivery.additional.param;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.impl.core.AbstractUnchangeableCrudDaoImpl;
import com.delivery.model.entity.bill.DeliveryType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DeliveryTypeDaoImplImpl extends AbstractUnchangeableCrudDaoImpl<DeliveryType> {
    private static final String FIND_BY_ID = "SELECT * FROM delivery WHERE name=?";
    private static final String DELETE_BY_ID = "DELETE FROM delivery WHERE name = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM delivery ";
    private static final String FIND_ALL = "SELECT * FROM delivery";
    private static final String FIND_BY_NAME = "SELECT * from delivery where name = ?";

    public DeliveryTypeDaoImplImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }

    @Override
    public DeliveryType mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return DeliveryType.valueOf(resultset.getString("name"));
    }

    @Override
    public Optional<DeliveryType> findById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    public DeliveryType findByName(String name){
        Optional<DeliveryType> deliveryType = findByParam(name, FIND_BY_NAME , STRING_PARAM_SETTER);
        return deliveryType.orElse(null);
    }
}
