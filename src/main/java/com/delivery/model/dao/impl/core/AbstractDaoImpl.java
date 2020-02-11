package com.delivery.model.dao.impl.core;

import com.delivery.model.dao.CrudDao;
import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.exeption.SqlRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDaoImpl<E> extends AbstractDao<E> implements CrudDao<E> {

    private final String findAllQueryLimit;
    private final String saveEntity;
    private final String updateAll;

    protected AbstractDaoImpl(DataBaseConnector connector, String findById, String deleteById, String countElements, String findAllQueryLimit, String save_entity, String update_all) {
        super(connector, findById, deleteById, countElements);
        this.findAllQueryLimit = findAllQueryLimit;
        saveEntity = save_entity;
        updateAll = update_all;
    }

    public void save(E entity) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(saveEntity)) {
            paramSet(entity, preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("Exception while trying to save new " + entity.getClass().getSimpleName(), e);
            throw new SqlRuntimeException(e.getMessage());
        }

    }

    public List<E> findAll(int page, int itemsPerPage) {
        try (Connection connection = connector.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(findAllQueryLimit)) {
            preparedStatement.setInt(1, (page - 1) * itemsPerPage);
            preparedStatement.setInt(2, itemsPerPage);

            return this.resultSet(preparedStatement);

        } catch (SQLException e) {
            LOGGER.error("Exception in findAll method", e);
            throw new SqlRuntimeException("Can't load data", e);
        }
    }

    public void update(E entity) {
        try (Connection connection = connector.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(updateAll)) {
            updateStatementSet(entity, preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SqlRuntimeException("Exception while trying to update user" + entity.toString(), e);
        }


    }


    protected abstract void updateStatementSet(E entity, PreparedStatement statement) throws SQLException;

    protected abstract void paramSet(E entity, PreparedStatement preparedStatement) throws SQLException;

}