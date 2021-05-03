package com.epam.web.service;

import com.epam.web.dao.*;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import com.epam.web.entity.Vacancy;
import com.epam.web.validator.VacancyValidator;

import java.util.List;

public class VacancyService extends AbstractService<Vacancy> {

    private final static int VACANCIES_ON_PAGE = 2;

    public VacancyService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, new VacancyValidator(), Vacancy.TABLE_NAME);
    }


    public List<Vacancy> getVacanciesByPage(int page) throws ServiceException {
        page--;
        int skipped = page * VACANCIES_ON_PAGE;
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            VacancyDao dao = (VacancyDao) helper.createDao(getDaoType());
            List<Vacancy> items = dao.getWithLimit(skipped, VACANCIES_ON_PAGE);
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            helper.startTransaction();
            RecruitingProcessDao processDao = (RecruitingProcessDao) helper.createDao(RecruitingProcess.TABLE_NAME);
            processDao.deleteVacancyLink(id);
            VacancyDao vacancyDao = (VacancyDao) helper.createDao(getDaoType());
            vacancyDao.removeById(id);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int getVacanciesOnPage() {
        return VACANCIES_ON_PAGE;
    }

}
