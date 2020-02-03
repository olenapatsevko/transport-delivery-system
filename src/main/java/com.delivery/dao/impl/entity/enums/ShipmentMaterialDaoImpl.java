package com.delivery.dao.impl.entity.enums;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.enums.ShipmentMaterialDao;
import com.delivery.dao.impl.entity.core.AbstractEnumCrudDaoImpl;
import com.delivery.entity.enums.shipment.Material;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentMaterialDaoImpl extends AbstractEnumCrudDaoImpl<Material> implements ShipmentMaterialDao {
    private static final String FIND_BY_ID = "SELECT * FROM material WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM material WHERE id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) AS count FROM material ";
    private static final String FIND_ALL = "SELECT * FROM material";


    public ShipmentMaterialDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);

    }

    @Override
    public Material mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return Material.valueOf(resultset.getString("name"));
    }
}
