package com.delivery.model.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataBaseConnector {
    private static final String PROPERTIES_FILE_NAME = "delivery_schema";
    private static final String CONNECTION_DRIVER = "db.driver";
    private static final String CONNECTION_URL = "db.url";
    private static final String CONNECTION_USERNAME = "db.user";
    private static final String CONNECTION_PASSWORD = "db.password";
    private static final String CONNECTION_POOL_SIZE = "db.pool.size";
    private static final String CONNECTION_TIMEOUT = "db.timeout";

    private HikariDataSource dataSource;

    public DataBaseConnector() {
        ResourceBundle resource = ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
        configConnection(resource);
    }


    public DataBaseConnector(String filename) {
        ResourceBundle resource = ResourceBundle.getBundle(filename);
        configConnection(resource);
    }

    private void configConnection(ResourceBundle resource) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(resource.getString(CONNECTION_DRIVER));
        config.setJdbcUrl(resource.getString(CONNECTION_URL));
        config.setUsername(resource.getString(CONNECTION_USERNAME));
        config.setPassword(resource.getString(CONNECTION_PASSWORD));
        config.setMaximumPoolSize(getProperty(resource, CONNECTION_POOL_SIZE));
        config.setConnectionTimeout(getProperty(resource, CONNECTION_TIMEOUT));
        this.dataSource = new HikariDataSource(config);
    }


    private int getProperty(ResourceBundle resource, String key) {
        return Integer.parseInt(resource.getString(key));
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeConnection() {
        dataSource.close();
    }
}
