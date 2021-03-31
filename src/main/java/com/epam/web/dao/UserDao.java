package com.epam.web.dao;

import com.epam.web.entity.User;
import com.epam.web.extractor.UserStatementExtractor;
import com.epam.web.mapper.UserRowMapper;

import java.sql.*;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    public final static String TABLE = "user";
    private final static String FIND_BY_USERNAME_AND_PASSWORD = "SELECT * FROM USER WHERE USERNAME = ?" +
            " AND PASSWORD = MD5(?)";

    public UserDao(Connection connection) {

        super(connection, new UserRowMapper(), new UserStatementExtractor());
    }

    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_USERNAME_AND_PASSWORD,
                username,
                password);
    }

    @Override
    protected String getTableName() {
        return TABLE;
    }

}
