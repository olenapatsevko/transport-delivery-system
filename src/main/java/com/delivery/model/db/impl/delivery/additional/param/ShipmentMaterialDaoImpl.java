package com.delivery.model.db.impl.delivery.additional.param;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.impl.core.AbstractEnumCrudDaoImpl;
import com.delivery.model.entity.OrderStatus;
import com.delivery.model.entity.bill.DeliveryType;
import com.delivery.model.entity.bill.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ShipmentMaterialDaoImpl extends AbstractEnumCrudDaoImpl<Material> {
    private static final String FIND_BY_ID = "SELECT * FROM material WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM material WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM material ";
    private static final String FIND_ALL = "SELECT * FROM material";
    private static final String FIND_BY_NAME = "SELECT * from material where name = ?";

    public ShipmentMaterialDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);

    }

    @Override
    public Material mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return Material.valueOf(resultset.getString("name"));
    }

    @Override
    public Optional<Material> findById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("This table has another primary key");
    }

    public Material findByName(String name) {
        Optional<Material> material = findByParam(name, FIND_BY_NAME, STRING_PARAM_SETTER);
        return material.orElse(null);
    }
}
