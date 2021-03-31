package com.epam.web.extractor;

import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.Role;

import java.util.Arrays;
import java.util.List;

public class UserStatementExtractor implements StatementExtractor<User> {
    private final static String INSERT_QUERY = "INSERT INTO USER(id, username, password, role, is_blocked)" +
            " values(?,?,MD5(?),?,?);";
    private final static String UPDATE_QUERY = "UPDATE USER SET username=?, password=MD5(?), role=?, is_blocked=?, " +
            "name=?, sex=?, age=?, photo=?, contacts=?, education=?, experience=?, skills=? where id=?;";
    // private final static String NULL = "null";


    @Override
    public List<Object> extractParamsForInsertQuery(User item) {

        return Arrays.asList(item.getId(),
                item.getUsername(),
                item.getPassword(),
                item.getRole().getValue(),
                item.isBlocked());
    }

    @Override
    public List<Object> extractParamsForUpdateQuery(User item) {
        Role role = item.getRole();
        String roleAsString = role.getValue();
        List<Object> userParams = Arrays.asList(
                item.getId(),
                item.getUsername(),
                item.getPassword(),
                roleAsString,
                item.isBlocked());
        List<Object> applicantParams;
        if (role == Role.APPLICANT) {
            applicantParams = extractApplicantParams(item);
        } else {
            applicantParams = Arrays.asList(null, null, null, null, null, null, null, null, item.getId());
        }
        userParams.addAll(applicantParams);
        return userParams;
    }

    @Override
    public String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    private List<Object> extractApplicantParams(User item) {
        Applicant applicant = (Applicant) item;
        return Arrays.asList(
                applicant.getName(),
                applicant.getGender().getValue(),
                applicant.getAge(),
                applicant.getPhoto(),
                applicant.getContacts(),
                applicant.getEducation(),
                applicant.getExperience(),
                applicant.getSkills(),
                applicant.getId());
    }
}
