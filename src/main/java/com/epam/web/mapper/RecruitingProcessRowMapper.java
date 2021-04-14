package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.enums.ApplicantState;
import com.epam.web.enums.EnumParsingException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecruitingProcessRowMapper implements RowMapper<RecruitingProcess> {

    public final static String USER_ID = "user_id";
    public final static String VACANCY_ID = "vacancy_id";
    public final static String STATE = "state";
    public final static String PRELIMINARY_POINTS = "preliminary_points";


    @Override
    public RecruitingProcess map(ResultSet resultSet) throws SQLException, DaoException {

        try {
            Long user_id = resultSet.getLong(USER_ID);
            Long vacancy_id = resultSet.getLong(VACANCY_ID);
            String stateAsString = resultSet.getString(STATE);
            ApplicantState state = ApplicantState.fromString(stateAsString);
            Integer preliminaryPoints = resultSet.getInt(PRELIMINARY_POINTS);
            return new RecruitingProcess(user_id, vacancy_id, state, preliminaryPoints);

        } catch (EnumParsingException e) {
            throw new DaoException(e);
        }
    }

}
