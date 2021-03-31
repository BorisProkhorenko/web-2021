package com.epam.web.dao;

import com.epam.web.entity.Response;
import com.epam.web.extractor.ResponseStatementExtractor;
import com.epam.web.extractor.VacancyStatementExtractor;
import com.epam.web.mapper.ResponseRowMapper;
import com.epam.web.mapper.VacancyRowMapper;

import java.sql.Connection;

public class ResponseDao extends AbstractDao<Response>{


    public final static String TABLE = "response";

    public ResponseDao(Connection connection) {

        super(connection, new ResponseRowMapper(), new ResponseStatementExtractor());
    }

    @Override
    protected String getTableName() {
        return TABLE;
    }
}
