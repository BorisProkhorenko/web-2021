package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.ApplicantState;
import com.epam.web.enums.EnumParsingException;
import com.epam.web.enums.Gender;
import com.epam.web.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRowMapper implements RowMapper<User> {

    public final static String ID = "id";
    public final static String USERNAME = "username";
    public final static String PASSWORD = "password";
    public final static String ROLE = "role";
    public final static String IS_BLOCKED = "is_blocked";


    @Override
    public User map(ResultSet resultSet) throws SQLException, DaoException {
        Long id = resultSet.getLong(ID);
        String username = resultSet.getString(USERNAME);
        String password = resultSet.getString(PASSWORD);
        boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
        String roleAsString = resultSet.getString(ROLE);
        Role role = getRoleFromString(roleAsString);
        User user = new User(id, username, password, role, isBlocked);
        return createEntity(user, resultSet);

    }

    protected User createEntity(User user, ResultSet resultSet) throws SQLException, DaoException {
        if (user.getRole() == Role.APPLICANT) {
            return new ApplicantRowMapper().createEntity(user, resultSet);
        }
        return user;
    }


    private Role getRoleFromString(String role) throws DaoException {
        try {
            return Role.fromString(role);
        } catch (EnumParsingException e) {
            throw new DaoException(e);
        }
    }


}
