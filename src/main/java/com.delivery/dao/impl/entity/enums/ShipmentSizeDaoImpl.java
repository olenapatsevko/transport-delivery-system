package com.delivery.dao.impl.entity.enums;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.enums.ShipmentSizeDao;
import com.delivery.dao.impl.entity.core.AbstractEnumCrudDaoImpl;
import com.delivery.entity.enums.shipment.Size;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentSizeDaoImpl extends AbstractEnumCrudDaoImpl<Size> implements ShipmentSizeDao {
    private static final String FIND_BY_ID = "SELECT * FROM size WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM size WHERE id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) AS count FROM size ";
    private static final String FIND_ALL = "SELECT * FROM size";


    public ShipmentSizeDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }

    @Override
    public Size mapResultSetToEntity(ResultSet resultset) throws SQLException {
        return Size.valueOf(resultset.getString("name"));
    }
}
