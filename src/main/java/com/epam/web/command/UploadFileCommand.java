package com.epam.web.command;


import com.epam.web.service.ApplicantService;
import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class UploadFileCommand implements Command {

    private static final String CV = "cv";
    private static final String ID = "id";
    private static final String PHOTOS = "photos";
    private static final String PNG = "png";
    private static final String DELIMITER = "/";
    private final ApplicantService service;

    public UploadFileCommand(ApplicantService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Long id = (Long) session.getAttribute(ID);
            String fileName = uploadFile(request);
            String filePath = request.getContextPath() + DELIMITER + PHOTOS + DELIMITER + fileName;

            service.updatePhoto(filePath, id);
        } catch (ServiceException | ServletException | IOException | FileUploadException e) {
            LOGGER.error(e.getMessage());
        }

        return CommandResult.redirect(CV);
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException, FileUploadException {
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> multiParts = servletFileUpload.parseRequest(request);
        FileItem item = multiParts.get(0);
        String fileName = item.getName();
        InputStream fileContent = item.getInputStream();
        BufferedImage image = ImageIO.read(fileContent);
        ServletContext servletContext = request.getServletContext();
        File uploads = new File(servletContext.getRealPath(PHOTOS) + DELIMITER + fileName);
        ImageIO.write(image, PNG, uploads);
        return fileName;

    }

}
