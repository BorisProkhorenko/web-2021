package com.epam.web.mapper;

import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.VacancyDao;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.User;
import com.epam.web.entity.Vacancy;
import com.epam.web.enums.ApplicantState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RecruitingProcessRowMapper implements RowMapper<RecruitingProcess> {
    private final static String ID = "id";
    private final static String USER_ID = "user_id";
    private final static String VACANCY_ID = "vacancy_id";
    private final static String STATE = "state";
    private final static String RATING = "rating";
    public final UserDao userDao = UserDao.getInstance();
    public final VacancyDao vacancyDao = VacancyDao.getInstance();

    public RecruitingProcessRowMapper() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        if (vacancyDao.getConnection() == null){
            Connection connection = connectionPool.getConnection();
            vacancyDao.setConnection(connection);
        }
        if (userDao.getConnection() == null){
            Connection connection = connectionPool.getConnection();
            userDao.setConnection(connection);
        }
    }

    @Override
    public RecruitingProcess map(ResultSet resultSet) throws SQLException, DaoException {
        Long id = resultSet.getLong(ID);
        Long user_id = resultSet.getLong(USER_ID);
        Long vacancy_id = resultSet.getLong(VACANCY_ID);
        Optional<User> optionalUser = userDao.getById(user_id);
        Optional<Vacancy> optionalVacancy = vacancyDao.getById(vacancy_id);
        String stateAsString = resultSet.getString(STATE);
        ApplicantState state = ApplicantState.fromString(stateAsString);
        Integer rating = resultSet.getInt(RATING);
        if (optionalUser.isPresent() && optionalVacancy.isPresent()) {
            User user = optionalUser.get();
            Vacancy vacancy = optionalVacancy.get();
            return new RecruitingProcess(id, user, vacancy, state, rating);
        } else if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new RecruitingProcess(id, user, null, state, rating);
        } else {
            throw new DaoException("Empty user");
        }

    }

}
