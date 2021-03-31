package com.epam.web.connection;


import com.epam.web.loader.PropertyLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final static String PROPERTY_FILE = "db.properties";

    private String url;
    private String login;
    private String password;

    public ConnectionFactory() {
        initializeProperties();
    }

    public Connection create() throws SQLException {

        return DriverManager.getConnection(url, login, password);
    }

    private void initializeProperties() {

        try {
            Properties properties = new PropertyLoader().load(PROPERTY_FILE);

            String driver = properties.getProperty("db.driver");
            Class.forName(driver);

            url = properties.getProperty("db.url");
            login = properties.getProperty("db.login");
            password = properties.getProperty("db.password");

        } catch (ClassNotFoundException | IOException e) {
            throw new ConnectionException(e);
        }
    }
}
