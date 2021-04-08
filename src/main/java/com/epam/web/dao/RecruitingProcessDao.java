package com.epam.web.dao;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import com.epam.web.enums.ApplicantState;
import com.epam.web.mapper.RecruitingProcessRowMapper;


import java.sql.Connection;
import java.util.ArrayList;
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
    public void save(RecruitingProcess item) throws DaoException {
        ArrayList<Object> paramList = new ArrayList<>(extractParams(item));
        Long userId = item.getId();
        Long vacancyId = item.getVacancyId();
        if (getById(userId).isPresent()) {//TODO USER_ID and Vacancy_ID
            paramList.add(userId);
            paramList.add(vacancyId);
        } else {
            paramList.set(0, userId);
            paramList.set(1, vacancyId);
        }
        Object[] params = paramList.toArray();
        executeUpdate(getUpdateQuery(), params);
    }

    public void insert(RecruitingProcess item) throws DaoException{
        List<Object> params = extractParams(item);
        Long userId = item.getId();
        Long vacancyId = item.getVacancyId();
        params.set(0, userId);
        params.set(1, vacancyId);
        executeUpdate(getInsertQuery(), params);
    }

    public void update(RecruitingProcess item) throws DaoException {
        List<Object> params = extractParams(item);
        Long userId = item.getId();
        Long vacancyId = item.getVacancyId();
        params.add(userId);
        params.add(vacancyId);
        executeUpdate(getUpdateQuery(), params);
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
