package com.epam.web.command;

import com.epam.web.entity.Response;

import com.epam.web.service.ResponseService;
import com.epam.web.service.ServiceException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetResponsesByUserCommand implements Command{

    private final ResponseService service;

    public GetResponsesByUserCommand(ResponseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");
        List<Response> responseList = service.getResponsesByUserId(id);
        for (Response r:responseList) {
            System.out.println(r);
        }
        request.setAttribute("responseList", responseList);
        return CommandResult.redirect("responses");
    }
}
