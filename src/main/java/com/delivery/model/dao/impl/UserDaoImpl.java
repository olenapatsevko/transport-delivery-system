package com.delivery.model.dao.impl;


import com.delivery.model.dao.DataBaseConnector;
import com.delivery.model.dao.impl.core.AbstractDaoImpl;
import com.delivery.model.entity.Role;
import com.delivery.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UserDaoImpl extends AbstractDaoImpl<User> implements com.delivery.model.dao.UserDao {
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM user ";
    private static final String FIND_ALL_LIMIT = "SELECT * FROM user LIMIT ?, ?";
    private static final String UPDATE_ALL = "UPDATE user SET  name = ? , surname = ? , password = ? , phone = ? , email = ?   WHERE id = ?";

    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";
    private static final String SAVE_ENTITY = "INSERT into user (id, name, surname, password, phone, email, role) VALUES (null, ?, ?, ?, ?, ?, ?)";


    public UserDaoImpl(DataBaseConnector connector) {
        super(connector, FIND_BY_ID, DELETE_BY_ID, COUNT_ALL, FIND_ALL_LIMIT, SAVE_ENTITY, UPDATE_ALL);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return findByParam(email, FIND_BY_EMAIL_QUERY, STRING_PARAM_SETTER);

    }

    @Override
    protected void paramSet(User entity, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getSecondName());
        preparedStatement.setString(3, entity.getPassword());
        preparedStatement.setString(4, entity.getPhone());
        preparedStatement.setString(5, entity.getEmail());
        preparedStatement.setBoolean(6, entity.getRole().equals(Role.USER));
    }

    @Override
    protected void updateStatementSet(User entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getSecondName());
        preparedStatement.setString(3, entity.getPassword());
        preparedStatement.setString(4, entity.getPhone());
        preparedStatement.setString(5, entity.getEmail());
        preparedStatement.setInt(6, entity.getId());
    }

    protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .withId(resultSet.getInt("id"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withPhone(resultSet.getString("phone"))
                .withFirstName(resultSet.getString("name"))
                .withSecondName(resultSet.getString("surname"))
                .withRole(resultSet.getBoolean("role")? Role.USER:Role.ADMIN)
                .build();
    }




}