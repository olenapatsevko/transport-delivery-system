package com.delivery.dao.impl.entity.enums;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.enums.ShipmentWeightDao;
import com.delivery.dao.impl.entity.core.AbstractEnumCrudDaoImpl;
import com.delivery.entity.enums.shipment.Weight;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentWeightDaoImpl extends AbstractEnumCrudDaoImpl<Weight> implements ShipmentWeightDao {
    private static final String FIND_BY_ID = "SELECT * FROM weight WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM weight WHERE id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) AS count FROM weight ";
    private static final String FIND_ALL = "SELECT * FROM weight";

    public ShipmentWeightDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }

    @Override
    public Weight mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return Weight.valueOf(resultset.getString("name"));
    }
}
