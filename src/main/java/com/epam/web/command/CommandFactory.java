package com.epam.web.command;

import com.epam.web.dao.UserDao;
import com.epam.web.service.UserService;

public class CommandFactory {
    private final static String LOGIN = "login";
    private final static String MAIN = "mainPage";
    private final static String CV = "cv";
    private final static String MESSAGES = "messages";
    private final static String LOGOUT = "logout";

    public Command create(String type){
        switch (type){
            case LOGIN:
                return new LoginCommand(new UserService(new UserDao()));
            case MAIN:
                return new ShowPageCommand("WEB-INF/view/main.jsp");
            case CV:
                return new ShowPageCommand("WEB-INF/view/cv.jsp");
            case MESSAGES:
                return new ShowPageCommand("WEB-INF/view/messages.jsp");
            case LOGOUT:
                return new LogoutCommand();
            default:
                throw new IllegalArgumentException("Unknown type of command" + type);
        }
    }
}
