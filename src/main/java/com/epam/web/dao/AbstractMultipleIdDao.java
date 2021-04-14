package com.epam.web.dao;

import com.epam.web.entity.MultipleId;
import com.epam.web.mapper.RowMapper;

import java.sql.Connection;
import java.util.Optional;

public abstract class AbstractMultipleIdDao<T extends MultipleId> extends AbstractDao<T> implements MultipleIdDao<T> {

    private final static String MULTIPLE_WHERE_ID = " WHERE USER_ID = ? AND VACANCY_ID = ?";

    protected AbstractMultipleIdDao(Connection connection, RowMapper<T> mapper) {
        super(connection, mapper);
    }

    @Override
    public Optional<T> getById(Long userId, Long vacancyId) throws DaoException {
        return executeForSingleResult(concatQuery(SELECT_ALL_FROM, MULTIPLE_WHERE_ID), userId, vacancyId);
    }

    @Override
    public void removeById(Long userId, Long vacancyId) throws DaoException {
        executeUpdate(concatQuery(DELETE_FROM, MULTIPLE_WHERE_ID), userId, vacancyId);
    }
}
