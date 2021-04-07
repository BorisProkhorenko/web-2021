package com.epam.web.mapper;

import com.epam.web.entity.Response;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ResponseRowMapper implements RowMapper<Response>{

    public final static String ID = "id";
    public final static String SUBJECT = "subject";
    public final static String DETAILS = "details";
    public final static String DATE = "date";
    public final static String USER_ID = "user_id";
    public final static String VACANCY_ID = "vacancy_id";


    @Override
    public Response map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String subject = resultSet.getString(SUBJECT);
        String details = resultSet.getString(DETAILS);
        Date date = resultSet.getTimestamp(DATE);
        Long user_id = resultSet.getLong(USER_ID);
        Long vacancy_id = resultSet.getLong(VACANCY_ID);
        return new Response(id, subject, details, date, user_id, vacancy_id);

    }



}
