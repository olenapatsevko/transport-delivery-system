package com.delivery.model.db.impl;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.CrudDao;
import com.delivery.model.db.impl.core.AbstractEnumCrudDaoImpl;
import com.delivery.model.entity.Place;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceDaoImpl extends AbstractEnumCrudDaoImpl<Place> implements CrudDao<Place> {
    private static final String FIND_BY_ID = "SELECT c.name as country,  r.name as region , t.name as town , t.id as id  from town t inner join region r on t.region = r.id inner join country c on r.country = c.id where t.id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM town WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*)  from town t inner join region r on t.region = r.id inner join country c on r.country = c.id ";
    private static final String FIND_ALL = "SELECT c.name as country,  r.name as region , t.name as town , t.id as id  from town t inner join region r on t.region = r.id inner join country c on r.country = c.id";


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