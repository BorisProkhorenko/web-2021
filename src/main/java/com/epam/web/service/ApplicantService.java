package com.epam.web.service;

import com.epam.web.dao.ApplicantDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.entity.Applicant;


public class ApplicantService extends UserService {


    public void updateCv(Applicant applicant) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            ApplicantDao applicantDao = (ApplicantDao) helper.createDao(getDaoType());
            applicantDao.save(applicant);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updatePhoto(String photo, Long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            ApplicantDao applicantDao = (ApplicantDao) helper.createDao(getDaoType());
            applicantDao.updatePhoto(photo, id);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    protected String getDaoType() {
        return DaoHelper.APPLICANT;
    }
}
