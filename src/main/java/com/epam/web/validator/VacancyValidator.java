package com.epam.web.validator;

import com.epam.web.entity.Vacancy;


public class VacancyValidator implements Validator<Vacancy> {

    private final static String NAME_PATTERN = "[а-яА-ЯёЁa-zA-ZäöüÄÖÜß ]{1,50}";
    private final static int MAX_SALARY = 50;
    private final static int MAX = 1000;

    @Override
    public boolean validate(Vacancy item) {
        String name = item.getName();
        String salary = item.getSalary();
        int salaryLength = salary.length();
        String responsibility = item.getResponsibility();
        int responsibilityLength = responsibility.length();
        String description = item.getDescription();
        int descriptionLength = description.length();
        String requirements = item.getRequirements();
        int requirementsLength = requirements.length();

        return name.matches(NAME_PATTERN) && salaryLength <= MAX_SALARY && requirementsLength <= MAX &&
                responsibilityLength <= MAX && descriptionLength <= MAX;

    }
}
