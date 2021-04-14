package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService service;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ID = "id";
    private static final String PAGE = "pageIndex";
    private static final String DEFAULT_PAGE = "1";
    private static final String MAIN = "mainPage";
    private static final String INVALID = "invalidLogin";
    private final static String ERROR_MESSAGE = "errorMessage";

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        Optional<User> optionalUser = null;
        try {
            optionalUser = service.login(username, password);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
        }
        return login(request, optionalUser);
    }

    private CommandResult login(HttpServletRequest request, Optional<User> optionalUser) {
        HttpSession session = request.getSession();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Long id = user.getId();
            session.setAttribute(ID, id);
            session.setAttribute(PAGE, DEFAULT_PAGE);
            return CommandResult.redirect(MAIN);
        } else {
            session.setAttribute(ERROR_MESSAGE, true);
            return CommandResult.redirect(INVALID);
        }
    }
}
