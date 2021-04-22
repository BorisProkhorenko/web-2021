package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.RecruitingProcess;


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

}
