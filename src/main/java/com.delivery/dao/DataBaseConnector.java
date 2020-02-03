package com.delivery.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataBaseConnector {

    private final String url;
    private final String user;
    private final String password;

    public DataBaseConnector(String filename) {
        ResourceBundle resource = ResourceBundle.getBundle(filename);
        this.url = resource.getString("url");
        this.user = resource.getString("user");
        this.password = resource.getString("password");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
