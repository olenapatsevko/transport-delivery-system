package com.delivery.model.dao.impl.core;

import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.CrudDao;
import com.delivery.model.exeption.SqlRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class AbstractDao<E> implements CrudDao<E> {

    protected final DataBaseConnector connector;
    private final String FIND_BY_ID;
    private final String DELETE_BY_ID;
    private final String COUNT_ELEMENTS;


    protected AbstractDao(DataBaseConnector connector, String findById, String deleteById, String countElements) {
        this.connector = connector;
        FIND_BY_ID = findById;
        DELETE_BY_ID = deleteById;
        COUNT_ELEMENTS = countElements;

    }


    @Override
    public Optional<E> findById(Integer id) {
        return findByParam(id, FIND_BY_ID, INT_PARAM_SETTER);
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = connector.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_BY_ID)) {

            INT_PARAM_SETTER.accept(preparedStatement, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            throw new SqlRuntimeException("Exception while trying to delete element in by Id ", e);
        }
    }


    public long count() {
        try (Connection connection = connector.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ELEMENTS)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    return resultSet.getInt("count(*)");
                }
                    return 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in count method", e);
            throw new SqlRuntimeException("Count not process", e);
        }
    }

    protected <P> Optional<E> findByParam(P param, String findByParam, BiConsumer<PreparedStatement, P> designatedParamSetter) {
        try (Connection connection =  connector.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(findByParam)) {
            designatedParamSetter.accept(preparedStatement, param);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.debug("Exception while tying to find an object by id", e);
            throw new SqlRuntimeException("Exception in findByParam method ", e);
        }
        return Optional.empty();
    }

    protected List<E> resultSet(PreparedStatement preparedStatement) throws SQLException {
        try (final ResultSet resultSet = preparedStatement.executeQuery()) {
            List<E> result = new ArrayList<>();
            while (resultSet.next()) {
                final E optionalResult = mapResultSetToEntity(resultSet);

                result.add(optionalResult);
            }
            return result;
        }
    }


    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;
}
