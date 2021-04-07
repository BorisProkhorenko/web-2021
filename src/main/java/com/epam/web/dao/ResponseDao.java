package com.epam.web.dao;

import com.epam.web.entity.Response;
import com.epam.web.mapper.ResponseRowMapper;


import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class ResponseDao extends AbstractDao<Response>{


    public final static String TABLE = "response";

    private final static String INSERT_QUERY = "INSERT INTO RESPONSE(id, subject, details, " +
            "user_id, vacancy_id) values(?,?,?,?,?);";

    private final static String UPDATE_QUERY = "UPDATE RESPONSE SET subject=?, details=?, where id=?;";

    private final static String SELECT_BY_USER_ID = "SELECT * FROM RESPONSE WHERE USER_ID=?";

    public ResponseDao(Connection connection) {

        super(connection, new ResponseRowMapper());
    }


    public List<Response> getByUserId(Long id) throws DaoException {
        return executeQuery(SELECT_BY_USER_ID, id);
    }

    @Override
    protected List<Object> extractParams(Response item) {
        return Arrays.asList(
                item.getSubject(),
                item.getDetails());
    }

    @Override
    public void insert(Response item) throws DaoException{
        List<Object> params = extractParams(item);
        Long id = item.getId();
        Long userId = item.getUserId();
        Long vacancyId = item.getVacancyId();
        params.set(0, id);
        params.add(userId);
        params.add(vacancyId);
        executeForVoidResult(getInsertQuery(), params);
}

    @Override
    protected String getTableName() {
        return TABLE;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }
}
