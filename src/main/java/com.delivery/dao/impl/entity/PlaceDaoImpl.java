package com.delivery.dao.impl.entity;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.PlaceDao;
import com.delivery.dao.impl.entity.core.AbstractEnumCrudDaoImpl;
import com.delivery.entity.Place;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceDaoImpl extends AbstractEnumCrudDaoImpl<Place> implements PlaceDao {
    private static final String FIND_BY_ID = "SELECT * FROM town WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM town WHERE id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) AS count FROM town ";
    private static final String FIND_ALL = "SELECT * FROM town";


    protected PlaceDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }

    @Override
    public Place mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Place.builder()
                .withCounty(resultSet.getString("country"))
                .withRegion(resultSet.getString("region"))
                .withCity(resultSet.getString("town"))
                .build();
    }
}
