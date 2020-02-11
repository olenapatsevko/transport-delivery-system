package com.delivery.model.dao.impl;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.CrudDao;
import com.delivery.model.dao.impl.core.AbstractUnchangeableCrudDaoImpl;
import com.delivery.model.entity.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PlaceDaoImpl extends AbstractUnchangeableCrudDaoImpl<Place> implements CrudDao<Place> {
    private static final String FIND_BY_ID = "SELECT c.name as country,  r.name as region , t.name as town , t.id as id  from town t inner join region r on t.region = r.id inner join country c on r.country = c.id where t.id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM town WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*)  from town t inner join region r on t.region = r.id inner join country c on r.country = c.id ";
    private static final String FIND_ALL = "SELECT c.name as country,  r.name as region , t.name as town , t.id as id  from town t inner join region r on t.region = r.id inner join country c on r.country = c.id";
    private static final String FIND_BY_NAME = "SELECT c.name as country,  r.name as region , t.name as town , t.id as id from town t inner join region r on t.region = r.id inner join country c on r.country = c.id where t.name = ?";

    public PlaceDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL);
    }


    public Optional<Place> findByName(String name) {
        return findByParam(name, FIND_BY_NAME, STRING_PARAM_SETTER);

    }

    @Override
    public Place mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Place.builder()
                .withId(resultSet.getInt("id"))
                .withCounty(resultSet.getString("country"))
                .withRegion(resultSet.getString("region"))
                .withCity(resultSet.getString("town"))
                .build();
    }
}
