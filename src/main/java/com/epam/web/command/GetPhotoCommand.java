package com.epam.web.command;

import com.epam.web.entity.Applicant;
import com.epam.web.service.ApplicantService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetPhotoCommand implements Command {
    private final ApplicantService service;
    private final static String ID = "id";
    private final static String IMAGE_TYPE = "image/jpg";
    private final static String CV = "cv";

    public GetPhotoCommand(ApplicantService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException {
        String idParam = request.getParameter(ID);
        Long id = Long.parseLong(idParam);
        Applicant applicant = (Applicant) service.getById(id);
        String path = applicant.getPhoto();
        Path photoPath = Paths.get(path);
        byte[] imageData = Files.readAllBytes(photoPath);
        response.setContentType(IMAGE_TYPE);
        int dataLength = imageData.length;
        response.setContentLength(dataLength);
        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(imageData);
            outputStream.flush();
        }
        return CommandResult.redirect(CV);
    }
}
