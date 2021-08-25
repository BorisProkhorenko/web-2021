package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Vacancy;
import com.epam.web.validator.VacancyValidator;

import java.util.List;

public class VacancyService extends AbstractService<Vacancy> {

    private final static int VACANCIES_ON_PAGE = 4;

    public VacancyService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, new VacancyValidator(), Vacancy.TABLE_NAME);
    }


    public List<Vacancy> getVacanciesByPage(int page) throws ServiceException {
        page--;
        if (page < 0) {
            throw new ServiceException("Negative page");
        }
        int skipped = page * VACANCIES_ON_PAGE;
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            VacancyDao dao = (VacancyDao) helper.getDao(getDaoType());
            return dao.getWithLimit(skipped, VACANCIES_ON_PAGE);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        DaoHelper helper = getDaoHelperFactory().create();
        try {
            helper.startTransaction();
            RecruitingProcessDao processDao = (RecruitingProcessDao) helper.getDao(RecruitingProcess.TABLE_NAME);
            processDao.deleteVacancyLink(id);
            VacancyDao vacancyDao = (VacancyDao) helper.getDao(getDaoType());
            vacancyDao.removeById(id);
            helper.endTransaction();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                helper.rollbackTransaction();

            } catch (DaoException exception) {
                LOGGER.error(e.getMessage(), e);
                throw new ServiceException(e.getMessage(), e);
            }
            throw new ServiceException(e.getMessage(), e);
        } finally {
            helper.close();
        }
    }

    public int getVacanciesOnPage() {
        return VACANCIES_ON_PAGE;
    }

}
