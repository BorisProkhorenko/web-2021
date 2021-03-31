package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
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
    public final static String NAME = "name";
    public final static String GENDER = "gender";
    public final static String AGE = "age";
    public final static String PHOTO = "photo";
    public final static String CONTACTS = "contacts";
    public final static String EDUCATION = "education";
    public final static String EXPERIENCE = "experience";
    public final static String SKILLS = "skills";

    @Override
    public User map(ResultSet resultSet) throws SQLException, DaoException {
        Long id = resultSet.getLong(ID);
        String username = resultSet.getString(USERNAME);
        String password = resultSet.getString(PASSWORD);
        boolean isBlocked = resultSet.getBoolean(IS_BLOCKED);
        String roleAsString = resultSet.getString(ROLE);
        Role role = getRoleFromString(roleAsString);
        if (role == Role.APPLICANT) {
            return mapApplicant(resultSet, id, username, password, isBlocked);
        } else {
            return new User(id, username, password, role, isBlocked);
        }
    }

    private User mapApplicant(ResultSet resultSet, Long id, String username, String password, Boolean isBlocked)
            throws SQLException, DaoException {
        String name = resultSet.getString(NAME);
        String sexAsString = resultSet.getString(GENDER);
        Gender gender = getSexFromString(sexAsString);
        Integer age = resultSet.getInt(AGE);
        String photo = resultSet.getString(PHOTO);
        String contacts = resultSet.getString(CONTACTS);
        String education = resultSet.getString(EDUCATION);
        String experience = resultSet.getString(EXPERIENCE);
        String skills = resultSet.getString(SKILLS);
        return new Applicant(id, username, password, isBlocked, name, gender, age, photo,
                contacts, education, experience, skills);
    }

    private Role getRoleFromString(String role) throws DaoException {
        Optional<Role> optionalRole = Role.fromString(role);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        } else {
            throw new DaoException("Unknown role");
        }
    }

    private Gender getSexFromString(String sex) throws DaoException {
        Optional<Gender> optionalSex = Gender.fromString(sex);
        if (optionalSex.isPresent()) {
            return optionalSex.get();
        } else {
            throw new DaoException("Unknown sex");
        }
    }
}
