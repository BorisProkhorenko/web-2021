package com.epam.web.validator;

import com.epam.web.entity.Vacancy;
import org.junit.Assert;
import org.junit.Test;

public class VacancyValidatorTest {

    private final static VacancyValidator VALIDATOR = new VacancyValidator();
    private final static Long MOCK_ID = 1L;
    private final static String VALID_NAME_AND_SALARY = "Some text here Some text hereSome text hereSome text hereSome";
    private final static String EMPTY = "";
    private final static String VALID_OTHER = "Some text here Some text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here";
    private final static String OVERSIZE_OTHER = "Some text here Some text hereSome text hereSome text" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here" +
            "Some text hereSome text hereSome text hereSome text hereSome text hereSome text hereSome text here";

    @Test
    public void testValidVacancyShouldReturnTrue() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME_AND_SALARY, VALID_NAME_AND_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidVacancyWithInvalidNameShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_OTHER, VALID_NAME_AND_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }


    @Test
    public void testInvalidVacancyWithOversizeNameShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_OTHER, VALID_NAME_AND_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithOversizeSalaryShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME_AND_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithOversizeResponsibilityShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME_AND_SALARY, VALID_NAME_AND_SALARY, OVERSIZE_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithOversizeDescriptionShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME_AND_SALARY, VALID_NAME_AND_SALARY, VALID_OTHER, OVERSIZE_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithOversizeRequirementsShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, VALID_NAME_AND_SALARY, VALID_NAME_AND_SALARY, VALID_OTHER, VALID_OTHER, OVERSIZE_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidVacancyWithEmptyNameShouldReturnFalse() {
        //given
        Vacancy vacancy = new Vacancy(MOCK_ID, EMPTY, VALID_NAME_AND_SALARY, VALID_OTHER, VALID_OTHER, VALID_OTHER);
        //when
        boolean isValid = VALIDATOR.validate(vacancy);
        //then
        Assert.assertFalse(isValid);
    }
}
