package com.delivery.dao.impl.entity;


import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.AccountDao;
import com.delivery.dao.impl.entity.core.AbstractCrudDaoImpl;
import com.delivery.entity.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountDaoImpl extends AbstractCrudDaoImpl<Account> implements AccountDao {
    private static final String FIND_BY_ID = "SELECT * FROM account where id = ?";
    private static final String DELETE_BY_ID = "SELECT * FROM account";
    private static final String COUNT_ALL = "SELECT COUNT(*) AS count FROM account ";
    private static final String FIND_ALL_LIMIT = "SELECT * FROM account LIMIT (?, ?)";
    private static final String SAVE_ENTITY = "INSERT INTO account (id, user) values (?, ?)";
    private static final String UPDATE_ALL = "UPDATE account SET user = ?   WHERE id = ?";


    public AccountDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL_LIMIT, SAVE_ENTITY, UPDATE_ALL);
    }


    @Override
    protected void updateStatementSet(Account entity, PreparedStatement statement) throws SQLException {
        statement.setInt(1, entity.getId());
        statement.setInt(2, entity.getUser().getId());

    }

    @Override
    protected void paramSet(Account entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, entity.getUser().getId());
        preparedStatement.setInt(2, entity.getId());
    }

    protected Account mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Account(resultSet.getInt("id"),
                new UserDaoImpl(this.connector).findById(resultSet.getInt("user")).get());

    }
}