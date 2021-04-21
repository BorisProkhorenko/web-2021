package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Vacancy;


import java.util.List;

public class RecruitingProcessService extends AbstractService<RecruitingProcess> {

    public RecruitingProcessService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, RecruitingProcess.TABLE_NAME);
    }

    public List<RecruitingProcess> getByVacancyId(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            RecruitingProcessDao dao = (RecruitingProcessDao) helper.createDao(getDaoType());
            List<RecruitingProcess> items = dao.getByVacancyId(id);
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
