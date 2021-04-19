package com.epam.web.command;

import com.epam.web.entity.Vacancy;
import com.epam.web.service.ServiceException;
import com.epam.web.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetVacancyCommand implements Command{

    private final VacancyService service;

    public GetVacancyCommand(VacancyService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idStr =  request.getParameter("id");
        Long id = Long.parseLong(idStr);
        Vacancy vacancy = service.getById(id);
        request.setAttribute("vacancy", vacancy);
        return null;
    }


}
