package com.epam.web.controller;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoHelperFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final CommandFactory commandFactory;
    private final static String COMMAND = "command";
    private final static String ERROR_MESSAGE = "errorMessage";
    private final static String ERROR_PAGE = "error.jsp";
    private final static String COMMAND_HEADER = "/controller?command=";

    public Controller() {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        commandFactory = new CommandFactory(daoHelperFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandType = request.getParameter(COMMAND);
        Command command = commandFactory.create(commandType);
        String page;
        boolean isRedirect = false;
        try {
            CommandResult result = command.execute(request, response);
            page = result.getPage();
            isRedirect = result.isRedirect();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(ERROR_MESSAGE, e.getMessage());
            page = ERROR_PAGE;
        }

        if (isRedirect) {
            String contextPath = getServletContext().getContextPath();
            String pagePath = contextPath + COMMAND_HEADER + page;
            response.sendRedirect(pagePath);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }


}
