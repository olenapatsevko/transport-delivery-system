package com.delivery.dao.impl.entity.core;

import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.core.CrudDao;
import com.delivery.exeption.SqlRuntimeException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractCrudDaoImpl<E> extends AbstractDao<E> implements CrudDao<E> {

    private final String FIND_ALL_QUERY_LIMIT;
    private final String SAVE_ENTITY;
    private final String UPDATE_ALL;

    protected AbstractCrudDaoImpl(DataBaseConnector connector, String findById, String deleteById, String countElements, String findAllQueryLimit, String save_entity, String update_all) {
        super(connector, findById, deleteById, countElements);
        this.FIND_ALL_QUERY_LIMIT = findAllQueryLimit;
        SAVE_ENTITY = save_entity;
        UPDATE_ALL = update_all;
    }

    protected void save(E entity) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(SAVE_ENTITY)) {
            paramSet(entity, preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warn("Exception while trying to save new user", e);
            throw new SqlRuntimeException("Exception to save new user:", e);
        }

    }

    public List<E> findAll(int page, int itemsPerPage) {
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(FIND_ALL_QUERY_LIMIT)) {
            preparedStatement.setInt(1, (page - 1) * itemsPerPage);
            preparedStatement.setInt(2, itemsPerPage);

            return this.resultSet(preparedStatement);

        } catch (SQLException e) {
            LOGGER.error("Exception in findAll method", e);
            throw new SqlRuntimeException("Can't load data", e);
        }
    }

    public void update(E entity) {
        try (final PreparedStatement preparedStatement = connector.getConnection().prepareStatement(UPDATE_ALL)) {
            updateStatementSet(entity, preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SqlRuntimeException("Exception while trying to update user" + entity.toString(), e);
        }


    }

    protected abstract void updateStatementSet(E entity, PreparedStatement statement) throws SQLException;

    protected abstract void paramSet(E entity, PreparedStatement preparedStatement) throws SQLException;

}