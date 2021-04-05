package com.epam.web.dao;

import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.Gender;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class ApplicantDao extends UserDao {

    private final static String UPDATE_QUERY = "UPDATE USER SET username=?, password=MD5(?), role=?, is_blocked=?, " +
            "name=?, sex=?, age=?, photo=?, contacts=?, education=?, experience=?, skills=? where id=?;";

    public ApplicantDao(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Object> extractParams(User item) {
        List<Object> userParams = super.extractParams(item);
        List<Object> applicantParams = extractApplicantParams(item);
        userParams.addAll(applicantParams);
        return userParams;
    }

    private List<Object> extractApplicantParams(User item) {
        Applicant applicant = (Applicant) item;
        Gender gender = applicant.getGender();
        return Arrays.asList(
                applicant.getName(),
                gender.getValue(),
                applicant.getAge(),
                applicant.getPhoto(),
                applicant.getContacts(),
                applicant.getEducation(),
                applicant.getExperience(),
                applicant.getSkills(),
                applicant.getId());
    }
}
