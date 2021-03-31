package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Response;
import com.epam.web.entity.User;
import com.epam.web.entity.Vacancy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseRowMapper implements RowMapper<Response>{

    public final static String ID = "id";
    public final static String MAIN = "main";
    public final static String DETAILS = "details";
    public final static String DATE = "date";
    public final static String USER_ID = "user_id";
    public final static String VACANCY_ID = "vacancy_id";


    @Override
    public Response map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String main = resultSet.getString(MAIN);
        String details = resultSet.getString(DETAILS);
        String date = resultSet.getString(DATE);//TODO date not String
        Long user_id = resultSet.getLong(USER_ID);
        Long vacancy_id = resultSet.getLong(VACANCY_ID);
        return new Response(id, main, details, date, user_id, vacancy_id);

    }



}
