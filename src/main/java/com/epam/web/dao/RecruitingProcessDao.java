package com.epam.web.dao;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.enums.ApplicantState;
import com.epam.web.mapper.RecruitingProcessRowMapper;


import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class RecruitingProcessDao extends AbstractDao<RecruitingProcess> {

    public final static String TABLE = "user_vacancy";

    private final static String INSERT_QUERY = "INSERT INTO VACANCY(user_id, vacancy_id, state, preliminary_points, " +
            "values(?,?,?,?,?);";
    private final static String UPDATE_QUERY = "UPDATE VACANCY SET state=?, preliminary_points=?, " +
            "where user_id=? AND vacancy_id;";

    public RecruitingProcessDao(Connection connection) {

        super(connection, new RecruitingProcessRowMapper());
    }

    @Override
    protected List<Object> extractParams(RecruitingProcess item) {
        ApplicantState state = item.getState();
        return Arrays.asList(
                state.getValue(),
                item.getPreliminaryPoints());
    }

    @Override
    public void insert(RecruitingProcess item) throws DaoException{
        List<Object> params = extractParams(item);
        Long userId = item.getId();
        Long vacancyId = item.getVacancyId();
        params.set(0, userId);
        params.set(1, vacancyId);
        executeForVoidResult(getInsertQuery(), params);
    }

    public void update(RecruitingProcess item) throws DaoException {
        List<Object> params = extractParams(item);
        Long userId = item.getId();
        Long vacancyId = item.getVacancyId();
        params.add(userId);
        params.add(vacancyId);
        executeForVoidResult(getUpdateQuery(), params);
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
