package com.epam.web.dao;

import com.epam.web.entity.Identifiable;
import com.epam.web.mapper.RowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    protected static final Logger LOGGER = LogManager.getLogger();
    protected final static String SELECT_ALL_FROM = "SELECT * FROM ";
    protected final static String WHERE_ID = "WHERE ID = ? ";
    protected final static String DELETE_FROM = "DELETE FROM ";
    protected final static String LIMIT = "LIMIT ?, ? ";
    protected final static String DELIMITER = " ";
    private Connection connection;
    private final RowMapper<T> mapper;


    protected AbstractDao(RowMapper<T> mapper) {
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
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
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

    protected void executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    protected String concatQuery(String action, String condition) {
        String table = getTableName();
        StringBuilder builder = new StringBuilder(action)
                .append(table)
                .append(DELIMITER)
                .append(condition);
        return builder.toString();
    }

    protected String concatQuery(String action) {
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

    public List<T> getWithLimit(Integer numSkipped, Integer limit) throws DaoException {
        return executeQuery(concatQuery(SELECT_ALL_FROM, LIMIT), numSkipped, limit);
    }

    @Override
    public void save(T item) throws DaoException {
        ArrayList<Object> paramList = new ArrayList<>(extractParams(item));
        Long id = item.getId();
        String query = getInsertQuery();
        if (getById(id).isPresent()) {
            paramList.add(id);
            query = getUpdateQuery();
        }
        Object[] params = paramList.toArray();
        executeUpdate(query, params);
    }

    protected abstract List<Object> extractParams(T item);

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(concatQuery(DELETE_FROM, WHERE_ID), id);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    protected abstract String getTableName();

    protected abstract String getUpdateQuery();

    protected abstract String getInsertQuery();

}
