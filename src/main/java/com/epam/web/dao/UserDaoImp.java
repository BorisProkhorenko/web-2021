package com.epam.web.dao;

import com.epam.web.entity.User;

import java.sql.*;
import java.util.Optional;

public class UserDaoImp {

    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/hr_database?useUnicode=true&serverTimezone=UTC";
    private final static String ROOT = "root";
    private final static String LOGIN_REQUEST = "SELECT ID, NAME FROM USER WHERE USERNAME = ? AND PASSWORD = MD5(?)";
    private final static String ID = "id";
    private final static String NAME = "name";

    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException {
        try {
            Class.forName(DRIVER);//1 time
            Connection connection = DriverManager.getConnection
                    (URL, ROOT, ROOT);
            try (PreparedStatement statement = connection.prepareStatement(LOGIN_REQUEST)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Long id = resultSet.getLong(ID);
                        String name = resultSet.getString(NAME);
                        return Optional.of(new User(id, name));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }
}
