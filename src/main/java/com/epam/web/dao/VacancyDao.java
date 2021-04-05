package com.epam.web.dao;


import com.epam.web.entity.Vacancy;

import com.epam.web.mapper.VacancyRowMapper;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;


public class VacancyDao extends AbstractDao<Vacancy> {

    public final static String TABLE = "vacancy";

    private final static String INSERT_QUERY = "INSERT INTO VACANCY(id, name, salary, responsibility, description, " +
            "requirements) values(?,?,?,?,?,?);";

    private final static String UPDATE_QUERY = "UPDATE VACANCY SET name=?, salary=?, responsibility=?, description=?, " +
            "requirements=? where id=?;";

    public VacancyDao(Connection connection) {

        super(connection, new VacancyRowMapper());
    }

    @Override
    protected List<Object> extractParams(Vacancy item) {
        return Arrays.asList(
                item.getName(),
                item.getSalary(),
                item.getResponsibility(),
                item.getDescription(),
                item.getRequirements());
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
