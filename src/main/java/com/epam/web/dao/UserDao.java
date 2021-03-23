package com.epam.web.dao;

import com.epam.web.entity.User;

import java.sql.*;
import java.util.Optional;

public class UserDao {
    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr_database",
                    "root", "root");

            try (PreparedStatement statement = connection.prepareStatement
                    ("SELECT ID, NAME FROM USER WHERE USERNAME = ? AND PASSWORD = MD5(?)")) {

                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        String name = resultSet.getString("name");
                        return Optional.of(new User(id, name));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.empty();
    }
}
