package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserCommand implements Command{

    private final UserService service;

    public GetUserCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idParam =  request.getParameter("id");
        Long id = Long.parseLong(idParam);
        User user = service.getById(id);
        request.setAttribute("user", user);
        return CommandResult.redirect("empty");
    }
}
