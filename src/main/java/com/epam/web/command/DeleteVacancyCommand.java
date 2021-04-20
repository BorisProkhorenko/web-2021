package com.epam.web.command;

import com.epam.web.service.ServiceException;
import com.epam.web.service.VacancyService;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteVacancyCommand implements Command {

    private final VacancyService service;
    private static final String VACANCY_ID = "vacancyId";
    private static final String MAIN = "mainPage";

    public DeleteVacancyCommand(VacancyService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, FileUploadException {
        HttpSession session = request.getSession();
        String idParam = (String) session.getAttribute(VACANCY_ID);
        Long id = Long.parseLong(idParam);
        service.deleteById(id);
        return CommandResult.redirect(MAIN);
    }
}
