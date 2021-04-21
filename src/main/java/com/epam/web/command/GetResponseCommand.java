package com.epam.web.command;

import com.epam.web.entity.Response;
import com.epam.web.service.ResponseService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetResponseCommand implements Command{
    private final ResponseService service;
    private final static String ID="id";
    private final static String RESPONSE_ATTRIBUTE="jobResponse";
    private final static String EMPTY_PAGE="empty";


    public GetResponseCommand(ResponseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idParam =  request.getParameter(ID);
        Long id = Long.parseLong(idParam);
        Response jobResponse = service.getById(id);
        request.setAttribute(RESPONSE_ATTRIBUTE, jobResponse);
        return CommandResult.redirect(EMPTY_PAGE);
    }
}
