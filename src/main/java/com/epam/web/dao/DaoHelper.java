package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable{

    private ProxyConnection connection;

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public UserDao createUserDao(){
        return new UserDao(connection);
    }

    public VacancyDao createVacancyDao(){
        return new VacancyDao(connection);
    }

    @Override
    public void close() {
        connection.close();
    }

    public void startTransaction() throws DaoException{
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException{
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
