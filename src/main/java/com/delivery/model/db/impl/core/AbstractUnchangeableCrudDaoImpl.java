package com.delivery.model.db.impl.core;

import com.delivery.model.db.DataBaseConnector;
import com.delivery.model.db.CrudDao;
import com.delivery.model.exeption.SqlRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public abstract class AbstractUnchangeableCrudDaoImpl<E> extends AbstractDao<E> implements CrudDao<E> {

    private final String FIND_ALL;


    protected AbstractUnchangeableCrudDaoImpl(DataBaseConnector connector, String findByID, String deleteById, String countElem, String findAll) {
        super(connector, findByID, deleteById, countElem);
        FIND_ALL = findAll;
    }

    @Override
    public List<E> findAll() {
        try (Connection connection = connector.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)){

            return resultSet(preparedStatement);

        } catch (SQLException e) {

            LOGGER.error("Exception in findAll method", e);
            throw new SqlRuntimeException("Can't load data", e);
        }
    }


}
