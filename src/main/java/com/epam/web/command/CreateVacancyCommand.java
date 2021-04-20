package com.epam.web.command;

import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateVacancyCommand implements Command{

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, FileUploadException {
        HttpSession session = request.getSession();
        session.setAttribute("vacancyId","0");
        return CommandResult.redirect("editVacancy");
    }
}
