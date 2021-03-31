package com.epam.web.dao;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.extractor.RecruitingProcessStatementExtractor;
import com.epam.web.extractor.ResponseStatementExtractor;
import com.epam.web.mapper.RecruitingProcessRowMapper;
import com.epam.web.mapper.ResponseRowMapper;

import java.sql.Connection;

public class RecruitingProcessDao extends AbstractDao<RecruitingProcess> {

    public final static String TABLE = "user_vacancy";

    public RecruitingProcessDao(Connection connection) {

        super(connection, new RecruitingProcessRowMapper(), new RecruitingProcessStatementExtractor());
    }

    @Override
    protected String getTableName() {
        return TABLE;
    }
}
