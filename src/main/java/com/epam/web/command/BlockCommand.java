package com.epam.web.command;

import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockCommand implements Command{

    private final UserService service;
    private final static String ID="id";
    private final static String IS_BLOCKED="isBlocked";
    private final static String MAIN="mainPage";

    public BlockCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, FileUploadException {
        String idParam = request.getParameter(ID);
        Long id = Long.parseLong(idParam);
        String isBlockedParam = request.getParameter(IS_BLOCKED);
        boolean isBlocked = Boolean.parseBoolean(isBlockedParam);
        service.changeBlock(id,isBlocked);
        return CommandResult.redirect(MAIN);
    }
}
