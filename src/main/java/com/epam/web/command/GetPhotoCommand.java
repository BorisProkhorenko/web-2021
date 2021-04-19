package com.epam.web.command;

import com.epam.web.entity.Applicant;
import com.epam.web.service.ApplicantService;
import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetPhotoCommand implements Command {
    private final ApplicantService service;

    public GetPhotoCommand(ApplicantService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, FileUploadException {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("id");
        Applicant applicant = (Applicant) service.getById(id);
        String path = applicant.getPhoto();
        byte[] imageData = Files.readAllBytes(Paths.get(path));
        response.setContentType("image/jpg");
        response.setContentLength(imageData.length);
        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(imageData);
            outputStream.flush();
        }
        return CommandResult.redirect("cv");
    }
}
