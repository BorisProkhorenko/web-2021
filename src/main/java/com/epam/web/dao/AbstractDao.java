package com.epam.web.dao;

import com.epam.web.entity.Identifiable;
import com.epam.web.mapper.RowMapper;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final static String SELECT_ALL_FROM = "SELECT * FROM ";
    private final static String WHERE_ID = " WHERE ID = ? ";
    private final static String DELETE_FROM = " DELETE FROM ";

    private final Connection connection;
    private final RowMapper<T> mapper;

    protected AbstractDao(Connection connection, RowMapper<T> mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }


    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }

    }

    protected void executeForVoidResult(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private String concatQuery(String action, String condition) {
        String table = getTableName();
        StringBuilder builder = new StringBuilder(action);
        builder.append(table);
        builder.append(condition);
        return builder.toString();

    }

    private String concatQuery(String action) {
        return concatQuery(action, "");
    }

    @Override
    public List<T> getAll() throws DaoException {
        return executeQuery(concatQuery(SELECT_ALL_FROM));

    }

    @Override
    public Optional<T> getById(Long id) throws DaoException {
        return executeForSingleResult(concatQuery(SELECT_ALL_FROM, WHERE_ID), id);
    }

    @Override
    public List<T> getAllById(Long id) throws DaoException {
        return executeQuery(concatQuery(SELECT_ALL_FROM, WHERE_ID), id);

    }
    @Override
    public void save(T item) throws DaoException {
        Long id = item.getId();
        if (getById(id).isPresent()) {
            update(item);
        } else {
            insert(item);
        }
    }


    public void insert(T item) throws DaoException {
        List<Object> params = extractParams(item);
        Long id = item.getId();
        params.set(0, id);
        executeForVoidResult(getInsertQuery(), params);
    }


    public void update(T item) throws DaoException {
        List<Object> params = extractParams(item);
        Long id = item.getId();
        params.add(id);
        executeForVoidResult(getUpdateQuery(), params);
    }

    protected abstract List<Object> extractParams(T item);

    @Override
    public void removeById(Long id) throws DaoException {
        executeForVoidResult(concatQuery(DELETE_FROM, WHERE_ID), id);
    }

    protected abstract String getTableName();
    protected abstract String getUpdateQuery();
    protected abstract String getInsertQuery();

}
