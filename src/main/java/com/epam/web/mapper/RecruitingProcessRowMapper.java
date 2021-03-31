package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.enums.ApplicantState;
import com.epam.web.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RecruitingProcessRowMapper implements RowMapper<RecruitingProcess> {

    public final static String USER_ID = "user_id";
    public final static String VACANCY_ID = "vacancy_id";
    public final static String STATE = "state";
    public final static String PRELIMINARY_POINTS = "preliminary_points";
    public final static String TECHNICAL_POINTS = "technical_points";


    @Override
    public RecruitingProcess map(ResultSet resultSet) throws SQLException, DaoException {

        Long user_id = resultSet.getLong(USER_ID);
        Long vacancy_id = resultSet.getLong(VACANCY_ID);
        String stateAsString = resultSet.getString(STATE);
        ApplicantState state = getStateFromString(stateAsString);
        Integer preliminaryPoints = resultSet.getInt(PRELIMINARY_POINTS);
        Integer technicalPoints = resultSet.getInt(TECHNICAL_POINTS);

        return new RecruitingProcess(user_id, vacancy_id, state, preliminaryPoints, technicalPoints);

    }

    private ApplicantState getStateFromString(String state) throws DaoException {
        Optional<ApplicantState> optionalApplicantState = ApplicantState.fromString(state);
        if (optionalApplicantState.isPresent()) {
            return optionalApplicantState.get();
        } else {
            throw new DaoException("Unknown state");
        }
    }
}
