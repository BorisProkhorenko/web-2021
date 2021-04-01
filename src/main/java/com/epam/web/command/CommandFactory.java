package com.epam.web.command;

import com.epam.web.service.UserService;

public class CommandFactory {
    private final static String LOGIN = "login";
    private final static String INVALID_LOGIN = "invalidLogin";
    private final static String MAIN = "mainPage";
    private final static String CV = "cv";
    private final static String APPLICATIONS = "applications";
    private final static String LOGOUT = "logout";

    private final static String INDEX_PAGE = "index.jsp";
    private final static String MAIN_PAGE = "WEB-INF/view/main.jsp";
    private final static String CV_PAGE = "WEB-INF/view/cv.jsp";
    private final static String APPLICATIONS_PAGE = "WEB-INF/view/applications.jsp";

    public Command create(String type){
        switch (type){
            case LOGIN:
                return new LoginCommand(new UserService());
            case INVALID_LOGIN:
                return new ShowPageCommand(INDEX_PAGE);
            case MAIN:
                return new ShowPageCommand(MAIN_PAGE);
            case CV:
                return new ShowPageCommand(CV_PAGE);
            case APPLICATIONS:
                return new ShowPageCommand(APPLICATIONS_PAGE);
            case LOGOUT:
                return new LogoutCommand();
            default:
                throw new IllegalArgumentException("Unknown type of command" + type);
        }
    }
}
