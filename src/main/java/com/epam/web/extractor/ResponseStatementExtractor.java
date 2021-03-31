package com.epam.web.extractor;

import com.epam.web.entity.Response;

import java.util.Arrays;
import java.util.List;

public class ResponseStatementExtractor implements StatementExtractor<Response> {

    private final static String INSERT_QUERY = "INSERT INTO RESPONSE(id, main, details, " +
            "user_id, vacancy_id) values(?,?,?,?,?,?);";
    private final static String UPDATE_QUERY = "UPDATE RESPONSE SET main=?, details=?,  where id=?;";

    @Override
    public List<Object> extractParamsForInsertQuery(Response item) {
        return Arrays.asList(
                item.getId(),
                item.getMain(),
                item.getDetails(),
                item.getUserId(),
                item.getVacancyId());
    }

    @Override
    public List<Object> extractParamsForUpdateQuery(Response item) {
        return Arrays.asList(
                item.getMain(),
                item.getDetails(),
                item.getId());
    }

    @Override
    public String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

}
