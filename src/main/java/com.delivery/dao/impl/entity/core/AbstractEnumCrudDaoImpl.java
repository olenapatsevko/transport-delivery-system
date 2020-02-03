package com.delivery.dao.impl.entity.core;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.core.CrudDao;
import com.delivery.exeption.SqlRuntimeException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public abstract class AbstractEnumCrudDaoImpl<E> extends AbstractDao<E> implements CrudDao<E> {

    private final String FIND_ALL;


    protected AbstractEnumCrudDaoImpl(DataBaseConnector connector, String findByID, String deleteById, String countElem, String findAll) {
        super(connector, findByID, deleteById, countElem);
        FIND_ALL = findAll;
    }

    @Override
    public List<E> findAll() {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL)) {

            return resultSet(preparedStatement);

        } catch (SQLException e) {

            LOGGER.error("Exception in findAll method", e);
            throw new SqlRuntimeException("Can't load data", e);
        }
    }


}
