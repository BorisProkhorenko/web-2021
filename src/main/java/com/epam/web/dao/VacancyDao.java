package com.epam.web.dao;

import com.epam.web.entity.User;
import com.epam.web.entity.Vacancy;
import com.epam.web.extractor.UserStatementExtractor;
import com.epam.web.extractor.VacancyStatementExtractor;
import com.epam.web.mapper.UserRowMapper;
import com.epam.web.mapper.VacancyRowMapper;

import java.sql.Connection;


public class VacancyDao extends AbstractDao<Vacancy>{

    public final static String TABLE = "vacancy";

    public VacancyDao(Connection connection) {

        super(connection, new VacancyRowMapper(), new VacancyStatementExtractor());
    }

    @Override
    protected String getTableName() {
        return TABLE;
    }
}
