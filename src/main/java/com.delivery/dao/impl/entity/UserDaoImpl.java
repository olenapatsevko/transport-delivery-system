package com.delivery.dao.impl.entity;


import com.delivery.dao.DataBaseConnector;
import com.delivery.dao.entity.UserDao;
import com.delivery.dao.impl.entity.core.AbstractCrudDaoImpl;
import com.delivery.entity.User;
import com.delivery.exeption.SqlRuntimeException;
import com.delivery.service.PasswordEncryption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UserDaoImpl extends AbstractCrudDaoImpl<User> implements UserDao {
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE id = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) AS count FROM user ";
    private static final String FIND_ALL_LIMIT = "SELECT * FROM user LIMIT (?, ?)";
    private static final String UPDATE_ALL = "UPDATE user SET  name = ? , surname = ? , password = ? , phone = ? , email = ?   WHERE id = ?";

    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";
    private static final String SAVE_ENTITY = "INSERT INTO user (id, name, surname,  password, phone, email , role) values (NULL, ?, ?, ?, ?, ?, ?)";
    private static final String LOGIN_USER = "SELECT * FROM user WHERE email = ? and password = ?";


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
        preparedStatement.setString(3, PasswordEncryption.encrypt(entity.getPassword()));
        preparedStatement.setString(4, entity.getPhone());
        preparedStatement.setString(5, entity.getEmail());
        preparedStatement.setBoolean(6, entity.getRole().getVal());
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
                .build();
    }

    public Optional<User> loginUser (String login , String password){
        try (final PreparedStatement preparedStatement =
                     connector.getConnection().prepareStatement(LOGIN_USER)) {
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.debug("Exception while tying to login a user", e);
            throw new SqlRuntimeException( e.getMessage());
        }
        return Optional.empty();
    }



}