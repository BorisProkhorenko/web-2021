package com.epam.web.command;

import com.epam.web.entity.Response;
import com.epam.web.service.ResponseService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetResponseCommand implements Command{
    private final ResponseService service;

    public GetResponseCommand(ResponseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idParam =  request.getParameter("id");
        Long id = Long.parseLong(idParam);
        Response jobResponse = service.getById(id);
        request.setAttribute("jobResponse", jobResponse);
        return CommandResult.redirect("empty");
    }
}
