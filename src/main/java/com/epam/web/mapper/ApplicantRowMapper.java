package com.epam.web.mapper;


import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.Gender;
import com.epam.web.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantRowMapper extends UserRowMapper {
    private final static String NAME = "name";
    private final static String GENDER = "gender";
    private final static String AGE = "age";
    private final static String PHOTO = "photo";
    private final static String CONTACTS = "contacts";
    private final static String EDUCATION = "education";
    private final static String EXPERIENCE = "experience";
    private final static String SKILLS = "skills";


    @Override
    protected User createEntity(Long id, String username, String password, Role role, boolean isBlocked,
                                ResultSet resultSet) throws SQLException {

        String genderAsString = resultSet.getString(GENDER);
        Gender gender = Gender.fromString(genderAsString);
        String name = resultSet.getString(NAME);
        Integer age = resultSet.getInt(AGE);
        String photo = resultSet.getString(PHOTO);
        String contacts = resultSet.getString(CONTACTS);
        String education = resultSet.getString(EDUCATION);
        String experience = resultSet.getString(EXPERIENCE);
        String skills = resultSet.getString(SKILLS);
        return new Applicant(id, username, password, isBlocked, name, gender, age, photo, contacts, education,
                experience, skills);

    }

}
