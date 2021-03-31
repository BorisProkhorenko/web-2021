package com.epam.web.extractor;

import com.epam.web.entity.Vacancy;


import java.util.Arrays;
import java.util.List;

public class VacancyStatementExtractor implements StatementExtractor<Vacancy> {
    private final static String INSERT_QUERY = "INSERT INTO VACANCY(id, name, salary, responsibility, description, " +
            "requirements) values(?,?,?,?,?,?);";
    private final static String UPDATE_QUERY = "UPDATE VACANCY SET name=?, salary=?, responsibility=?, description=?, " +
            "requirements=? where id=?;";
    // Long id, String name, String salary, String responsibility, String description, String requirements;


    @Override
    public List<Object> extractParamsForInsertQuery(Vacancy item) {
        return Arrays.asList(
                item.getId(),
                item.getName(),
                item.getSalary(),
                item.getResponsibility(),
                item.getDescription(),
                item.getRequirements());
    }

    @Override
    public List<Object> extractParamsForUpdateQuery(Vacancy item) {
        return Arrays.asList(
                item.getName(),
                item.getSalary(),
                item.getResponsibility(),
                item.getDescription(),
                item.getRequirements(),
                item.getId());
    }

    @Override
    public String getInsertQuery() {
        return INSERT_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

}
