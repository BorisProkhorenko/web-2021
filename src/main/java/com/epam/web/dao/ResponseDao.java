package com.epam.web.dao;

import com.epam.web.entity.Response;
import com.epam.web.mapper.ResponseRowMapper;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseDao extends AbstractMultipleIdDao<Response> {


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
    public void save(Response item) throws DaoException {
        ArrayList<Object> paramList = new ArrayList<>(extractParams(item));
        Long id = item.getId();
        Long userId = item.getUserId();
        Long vacancyId = item.getVacancyId();
        if (getById(id).isPresent()) {
            paramList.add(id);
        } else {
            paramList.set(0, id);
            paramList.add(userId);
            paramList.add(vacancyId);
        }
        Object[] params = paramList.toArray();
        executeUpdate(getUpdateQuery(), params);
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
