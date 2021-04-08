package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection connection;
    public static final String USER = "user";
    public static final String VACANCY = "vacancy";
    public static final String RESPONSE = "response";
    public static final String RECRUITING_PROCESS = "recruitingProcess";

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public UserDao createUserDao() {
        return new UserDao(connection);
    }

    public ApplicantDao createApplicantDao() {
        return new ApplicantDao(connection);
    }

    public VacancyDao createVacancyDao() {
        return new VacancyDao(connection);
    }

    public ResponseDao createResponseDao() {
        return new ResponseDao(connection);
    }

    public AbstractDao createDao(String daoType) throws DaoException {
        switch (daoType) {
            case USER:
                return new UserDao(connection);
            case VACANCY:
                return new VacancyDao(connection);
            case RESPONSE:
                return new ResponseDao(connection);
            case RECRUITING_PROCESS:
                return new RecruitingProcessDao(connection);
            default:
                throw new DaoException("Unknown DAO type");
        }
    }

    @Override
    public void close() {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
