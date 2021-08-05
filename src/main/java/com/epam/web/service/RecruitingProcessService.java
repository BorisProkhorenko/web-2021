package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import com.epam.web.validator.RecruitingProcessValidator;
import com.epam.web.validator.ResponseValidator;

import java.util.List;

public class RecruitingProcessService extends AbstractService<RecruitingProcess> {

    private final ResponseValidator responseValidator;

    public RecruitingProcessService(DaoHelperFactory daoHelperFactory, ResponseValidator responseValidator) {
        super(daoHelperFactory, new RecruitingProcessValidator(), RecruitingProcess.TABLE_NAME);
        this.responseValidator = responseValidator;
    }

    public boolean isEmpty(Long userId, Long vacancyId) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            RecruitingProcessDao dao = (RecruitingProcessDao) helper.getDao(getDaoType());
            List<RecruitingProcess> items = dao.getByUserAndVacancyId(userId, vacancyId);
            return items.isEmpty();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ServiceException(e);
        }
    }

    public List<RecruitingProcess> getByVacancyId(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            RecruitingProcessDao dao = (RecruitingProcessDao) helper.getDao(getDaoType());
            return dao.getByVacancyId(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ServiceException(e);
        }
    }

    public void updateWithFeedback(RecruitingProcess process, Response feedback) throws ServiceException {
        if (getValidator().validate(process) &&
                responseValidator.validate(feedback)) {
            try (DaoHelper helper = getDaoHelperFactory().create()) {
                makeUpdateTransaction(helper, process, feedback);
            }

        } else if (!getValidator().validate(process)) {
            throw new ServiceException("invalid recruiting process");
        } else {
            throw new ServiceException("invalid feedback response");
        }
    }

    public void makeUpdateTransaction(DaoHelper helper, RecruitingProcess process, Response feedback) throws ServiceException {
        try {
            helper.startTransaction();
            RecruitingProcessDao processDao = (RecruitingProcessDao) helper.getDao(getDaoType());
            ResponseDao responseDao = (ResponseDao) helper.getDao(Response.TABLE_NAME);
            processDao.save(process);
            responseDao.save(feedback);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error("Transaction failed - trying to rollback. Cause - " + e.getMessage(),e);
            try {
                helper.rollbackTransaction();

            } catch (DaoException exception) {
                LOGGER.error(e.getMessage(),e);
                throw new ServiceException(e.getMessage(), e);
            }

            throw new ServiceException(e.getMessage(), e);
        }
    }

}
