package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.*;

import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;
    private final UserDao userDao = UserDao.getInstance();
    private final ApplicantDao applicantDao = ApplicantDao.getInstance();
    private final VacancyDao vacancyDao = VacancyDao.getInstance();
    private final RecruitingProcessDao recruitingProcessDao = RecruitingProcessDao.getInstance();
    private final ResponseDao responseDao = ResponseDao.getInstance();
    private final static Lock LOCK = new ReentrantLock();
    private int lockCounter = 0;


    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }

    public Dao getDao(String daoType) throws DaoException {
        switch (daoType) {
            case User.TABLE_NAME:
                return getUserDao();
            case Applicant.APPLICANT:
                return getApplicantDao();
            case Vacancy.TABLE_NAME:
                return getVacancyDao();
            case Response.TABLE_NAME:
                return getResponseDao();
            case RecruitingProcess.TABLE_NAME:
                return getRecruitingProcessDao();
            default:
                throw new DaoException("Unknown DAO type");
        }
    }

    public UserDao getUserDao() {
        LOCK.lock();
        lockCounter++;
        userDao.setConnection(connection);
        return userDao;
    }

    public ApplicantDao getApplicantDao() {
        LOCK.lock();
        lockCounter++;
        applicantDao.setConnection(connection);
        return applicantDao;
    }

    public VacancyDao getVacancyDao() {
        LOCK.lock();
        lockCounter++;
        vacancyDao.setConnection(connection);
        return vacancyDao;
    }

    public RecruitingProcessDao getRecruitingProcessDao() {
        LOCK.lock();
        lockCounter++;
        recruitingProcessDao.setConnection(connection);
        return recruitingProcessDao;
    }

    public ResponseDao getResponseDao() {
        LOCK.lock();
        lockCounter++;
        responseDao.setConnection(connection);
        return responseDao;
    }

    @Override
    public void close() {
        connection.close();
        while (lockCounter > 0) {
            LOCK.unlock();
            lockCounter--;
        }
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            AbstractDao.LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            AbstractDao.LOGGER.error(e.getMessage(), e);
            throw new DaoException(e);
        }
    }

    public void rollbackTransaction() throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            AbstractDao.LOGGER.error(e.getMessage(), e);
            throw new DaoException(e);
        }
    }

}
