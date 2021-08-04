package com.epam.web.validator;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ResponseValidatorTest {

    private final static ResponseValidator VALIDATOR = new ResponseValidator();
    private final static Long MOCK_ID = 1L;
    private final static String VALID = "Some textSome textSome textSome textSome textSome textSome";
    private final static String OVERSIZE = "Some text here Some text hereSome text hereSome text" +
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

    private final static RecruitingProcess MOCK_RECRUITING_PROCESS = null;
    private final static Date MOCK_DATE = null;

    @Test
    public void testValidResponseShouldReturnTrue() {
        //given
        Response response = new Response(MOCK_ID, VALID, VALID, MOCK_DATE, MOCK_RECRUITING_PROCESS);
        //when
        boolean isValid = VALIDATOR.validate(response);
        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testInvalidResponseOversizeSubjectShouldReturnFalse() {
        //given
        Response response = new Response(MOCK_ID, OVERSIZE, OVERSIZE, MOCK_DATE, MOCK_RECRUITING_PROCESS);
        //when
        boolean isValid = VALIDATOR.validate(response);
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testInvalidResponseOversizeDetailsShouldReturnFalse() {
        //given
        Response response = new Response(MOCK_ID, OVERSIZE, OVERSIZE, MOCK_DATE, MOCK_RECRUITING_PROCESS);
        //when
        boolean isValid = VALIDATOR.validate(response);
        //then
        Assert.assertFalse(isValid);
    }

}
