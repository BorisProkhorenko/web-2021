package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.EnumParsingException;
import com.epam.web.enums.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantRowMapper extends UserRowMapper {
    public final static String NAME = "name";
    public final static String GENDER = "gender";
    public final static String AGE = "age";
    public final static String PHOTO = "photo";
    public final static String CONTACTS = "contacts";
    public final static String EDUCATION = "education";
    public final static String EXPERIENCE = "experience";
    public final static String SKILLS = "skills";


    @Override
    protected User createEntity(User user, ResultSet resultSet) throws SQLException, DaoException {
        String name = resultSet.getString(NAME);
        String genderAsString = resultSet.getString(GENDER);
        Gender gender = getGenderFromString(genderAsString);
        Integer age = resultSet.getInt(AGE);
        String photo = resultSet.getString(PHOTO);
        String contacts = resultSet.getString(CONTACTS);
        String education = resultSet.getString(EDUCATION);
        String experience = resultSet.getString(EXPERIENCE);
        String skills = resultSet.getString(SKILLS);
        return new Applicant(user, name, gender, age, photo, contacts, education, experience, skills);
    }


    private Gender getGenderFromString(String gender) throws DaoException {
        try {
            return Gender.fromString(gender);
        } catch (EnumParsingException e) {
            throw new DaoException(e);
        }
    }
}
