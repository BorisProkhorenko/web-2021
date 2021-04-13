package com.epam.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    Logger LOGGER = LogManager.getLogger();
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
