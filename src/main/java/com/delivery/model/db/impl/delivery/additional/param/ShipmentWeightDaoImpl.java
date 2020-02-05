package com.delivery.model.db.impl.delivery.additional.param;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.impl.core.AbstractEnumCrudDaoImpl;
import com.delivery.model.entity.Shipment;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Size;
import com.delivery.model.entity.bill.Weight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ShipmentWeightDaoImpl extends AbstractEnumCrudDaoImpl<Weight> {
    private static final String FIND_BY_ID = "SELECT * FROM weight WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM weight WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM weight ";
    private static final String FIND_ALL = "SELECT * FROM weight";
    private static final String FIND_BY_NAME = "SELECT * from weight where name = ?";

    public ShipmentWeightDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }

    @Override
    public Weight mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return Weight.valueOf(resultset.getString("name"));
    }

    @Override
    public Optional<Weight> findById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    public Weight findByName(String name) {
        Optional<Weight> weight = findByParam(name, FIND_BY_NAME, STRING_PARAM_SETTER);
        return weight.orElse(null);
    }
}
