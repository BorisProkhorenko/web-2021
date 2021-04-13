package com.epam.web.command;

import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.EnumParsingException;
import com.epam.web.enums.Gender;
import com.epam.web.service.ApplicantService;
import com.epam.web.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CvEditCommand implements Command {

    private final ApplicantService applicantService;
    private static final String CV = "cv";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String GENDER = "gender";
    private static final String AGE = "age";
    private static final String PHOTO = "photo";
    private static final String CONTACTS = "contacts";
    private static final String EDUCATION = "education";
    private static final String EXPERIENCE = "experience";
    private static final String SKILLS = "skills";

    public CvEditCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute(ID);
        String name = request.getParameter(NAME);
        Integer age = Integer.parseInt(request.getParameter(AGE));
        String contacts = request.getParameter(CONTACTS);
        String education = request.getParameter(EDUCATION);
        String experience = request.getParameter(EXPERIENCE);
        String skills = request.getParameter(SKILLS);
        try {
            Gender gender = Gender.fromString(request.getParameter(GENDER));
            User user = applicantService.getById(id);
            Applicant applicant = new Applicant(user, name, gender, age, PHOTO, contacts, education, experience, skills);
            applicantService.updateCv(applicant);
        } catch (ServiceException | EnumParsingException e) {
            LOGGER.error(e.getMessage());
        }

        return CommandResult.redirect(CV);
    }


}
