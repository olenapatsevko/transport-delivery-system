package com.delivery.dao.impl.entity.enums;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.enums.DeliveryTypeDao;
import com.delivery.dao.impl.entity.core.AbstractEnumCrudDaoImpl;
import com.delivery.entity.enums.DeliveryType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryTypeCrudDaoImplImpl extends AbstractEnumCrudDaoImpl<DeliveryType> implements DeliveryTypeDao {
    private static final String FIND_BY_ID = "SELECT * FROM delivery WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM delivery WHERE id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) AS count FROM delivery ";
    private static final String FIND_ALL = "SELECT * FROM delivery";


    public DeliveryTypeCrudDaoImplImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }


    @Override
    public DeliveryType mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return DeliveryType.valueOf(resultset.getString("name"));
    }
}
