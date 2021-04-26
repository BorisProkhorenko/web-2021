package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import org.apache.commons.fileupload.FileItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ApplicantService extends UserService {


    private static final String JPG = "jpg";

    public ApplicantService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory,Applicant.APPLICANT);
    }


    public void updatePhoto(String photo, Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            ApplicantDao applicantDao = (ApplicantDao) helper.createDao(getDaoType());
            applicantDao.updatePhoto(photo, id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void uploadFile(FileItem item, String path) throws ServiceException {
        try {
            String fileName = item.getName();
            InputStream fileContent = item.getInputStream();
            BufferedImage image = ImageIO.read(fileContent);
            ImageIO.write(image,JPG, new File(path));
        }catch (IOException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

}
