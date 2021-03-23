package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> optionalUser = null;
        try {
            optionalUser = service.login(username, password);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        optionalUser.ifPresent(user -> request.getSession().setAttribute("name", user.getName()));

        return CommandResult.redirect("/web_2021_war/controller?command=mainPage");
    }
}
