package com.delivery.model.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public interface CrudDao<E> {

    Logger LOGGER = LogManager.getLogger(CrudDao.class);

    long count();

    Optional<E> findById(Integer id);

    BiConsumer<PreparedStatement, Integer> INT_PARAM_SETTER = (preparedStatement, integer) -> {
        try {
            preparedStatement.setInt(1, integer);

        } catch (SQLException e) {
            LOGGER.error("Exception during setting to prepared statement" + preparedStatement.toString() + "value" + integer, e);
        }
    };

    BiConsumer<PreparedStatement, String> STRING_PARAM_SETTER = (preparedStatement, string) -> {
        try {
            preparedStatement.setString(1, string);
        } catch (SQLException e) {
            LOGGER.error("Exception during setting to prepared statement" + preparedStatement.toString() + "value" + string, e);
        }
    };


    default List<E> findAll() {
        return Collections.emptyList();
    }

    void deleteById(Integer id);


}