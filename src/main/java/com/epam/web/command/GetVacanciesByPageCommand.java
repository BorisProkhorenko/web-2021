package com.epam.web.command;

import com.epam.web.entity.Vacancy;
import com.epam.web.service.ServiceException;
import com.epam.web.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

public class GetVacanciesByPageCommand implements Command {

    private final VacancyService service;

    public GetVacanciesByPageCommand(VacancyService service) {
        this.service = service;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute("pageIndex");
        int pageIndex = Integer.parseInt(page);
        List<Vacancy> vacancyList = service.getVacanciesByPage(pageIndex);
        request.setAttribute("vacancyList", vacancyList);
        request.setAttribute("VacanciesOnPage", service.getVacanciesOnPage());
        List<Vacancy> all = service.getAll();
        request.setAttribute("VacanciesCount", all.size());
        return CommandResult.redirect("mainPage");
    }
}
