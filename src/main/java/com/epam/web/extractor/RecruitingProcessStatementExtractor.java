package com.epam.web.extractor;

import com.epam.web.entity.RecruitingProcess;


import java.util.Arrays;
import java.util.List;

public class RecruitingProcessStatementExtractor implements StatementExtractor<RecruitingProcess> {

    private final static String INSERT_QUERY = "INSERT INTO VACANCY(user_id, vacancy_id, state, preliminary_points, " +
            "technical_points) values(?,?,?,?,?);";
    private final static String UPDATE_QUERY = "UPDATE VACANCY SET state=?, preliminary_points=?, " +
            "technical_points=? where user_id=? AND vacancy_id;";


    @Override
    public List<Object> extractParamsForInsertQuery(RecruitingProcess item) {
        return Arrays.asList(
                item.getId(),
                item.getVacancyId(),
                item.getState().getValue(),
                item.getPreliminaryPoints(),
                item.getTechnicalPoints());
    }

    @Override
    public List<Object> extractParamsForUpdateQuery(RecruitingProcess item) {
        return Arrays.asList(
                item.getState().getValue(),
                item.getPreliminaryPoints(),
                item.getTechnicalPoints(),
                item.getId(),
                item.getVacancyId());
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
