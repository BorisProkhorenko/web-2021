package com.epam.web.mapper;

import com.epam.web.entity.Vacancy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VacancyRowMapper implements RowMapper<Vacancy> {

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String SALARY = "salary";
    private final static String RESPONSIBILITY = "responsibility";
    private final static String DESCRIPTION = "description";
    private final static String REQUIREMENTS = "requirements";


    @Override
    public Vacancy map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String salary = resultSet.getString(SALARY);
        String responsibility = resultSet.getString(RESPONSIBILITY);
        String description = resultSet.getString(DESCRIPTION);
        String requirements = resultSet.getString(REQUIREMENTS);

        return new Vacancy(id, name, salary, responsibility, description, requirements);

    }
}
