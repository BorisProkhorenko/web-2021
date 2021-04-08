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
    private static final Logger LOGGER = LogManager.getLogger();

    public CvEditCommand(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");
        String name = request.getParameter("name");
        Integer age = Integer.parseInt(request.getParameter("age"));
        String contacts = request.getParameter("contacts");
        String education = request.getParameter("education");
        String experience = request.getParameter("experience");
        String skills = request.getParameter("skills");
        try {
            Gender gender = Gender.fromString(request.getParameter("gender"));
            User user = applicantService.getById(id);
            Applicant applicant = new Applicant(user, name, gender, age, "photo", contacts, education, experience, skills);
            applicantService.updateCv(applicant);
        } catch (ServiceException | EnumParsingException e) {
            LOGGER.error(e.getMessage());
        }

        return CommandResult.redirect(CV);
    }


}
