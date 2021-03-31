package com.epam.web.dao;

import com.epam.web.entity.Identifiable;
import com.epam.web.extractor.StatementExtractor;
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
    private final StatementExtractor<T> extractor;

    protected AbstractDao(Connection connection, RowMapper<T> mapper, StatementExtractor<T> extractor) {
        this.connection = connection;
        this.mapper = mapper;
        this.extractor = extractor;
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
    public void save(T item) throws DaoException {
        Long id = item.getId();
        if (getById(id).isPresent()) {
            update(item);
        } else {
            insert(item);
        }
    }

    private void insert(T item) throws DaoException {
        String query = extractor.getInsertQuery();
        List<Object> params = extractor.extractParamsForInsertQuery(item);
        executeForVoidResult(query, params);
    }

    private void update(T item) throws DaoException {
        String query = extractor.getUpdateQuery();
        List<Object> params = extractor.extractParamsForUpdateQuery(item);
        executeForVoidResult(query, params);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeForVoidResult(concatQuery(DELETE_FROM, WHERE_ID), id);
    }

    protected abstract String getTableName();

}
