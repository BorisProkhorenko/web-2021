package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;


import java.util.List;

public class RecruitingProcessService extends AbstractService<RecruitingProcess> {

    public RecruitingProcessService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, RecruitingProcess.TABLE_NAME);
    }

    public boolean isEmpty(Long userId, Long vacancyId) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            RecruitingProcessDao dao = (RecruitingProcessDao) helper.createDao(getDaoType());
            List<RecruitingProcess> items = dao.getByUserAndVacancyId(userId, vacancyId);
            return items.isEmpty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<RecruitingProcess> getByVacancyId(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            RecruitingProcessDao dao = (RecruitingProcessDao) helper.createDao(getDaoType());
            return dao.getByVacancyId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateWithFeedback(RecruitingProcess process, Response feedback) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            helper.startTransaction();
            RecruitingProcessDao processDao = (RecruitingProcessDao) helper.createDao(getDaoType());
            ResponseDao responseDao = (ResponseDao) helper.createDao(Response.TABLE_NAME);
            processDao.save(process);
            responseDao.save(feedback);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

}
