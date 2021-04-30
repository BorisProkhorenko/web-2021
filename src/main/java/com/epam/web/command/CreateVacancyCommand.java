package com.epam.web.command;

import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateVacancyCommand implements Command{

    private static final String VACANCY_ID = "vacancyId";
    private static final String EDIT = "editVacancy";
    private static final String NULL_ID = "0";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, FileUploadException {
        HttpSession session = request.getSession();
        session.setAttribute(VACANCY_ID, NULL_ID);
        return CommandResult.redirect(EDIT);
    }
}
