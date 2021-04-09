package com.epam.web.service;

import com.epam.web.dao.*;

import com.epam.web.entity.Vacancy;

import java.util.List;

public class VacancyService extends AbstractService<Vacancy> {

    public final static int VACANCIES_ON_PAGE = 2;

    public List<Vacancy> getVacancies() throws ServiceException {
        return super.getAll();
    }

    public List<Vacancy> getVacanciesByPage(int page) throws ServiceException {
        page--;
        int skipped = page * VACANCIES_ON_PAGE;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            VacancyDao dao = helper.createVacancyDao();
            List<Vacancy> items = dao.getWithLimit(skipped, VACANCIES_ON_PAGE);
            helper.endTransaction();
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public int getPagesCount() throws ServiceException {
        int items = getVacancies().size();
        if (items % VACANCIES_ON_PAGE == 0) {
            if (items / VACANCIES_ON_PAGE == 0) {
                return 1;
            } else {
                return items / VACANCIES_ON_PAGE;
            }

        } else {
            return items / VACANCIES_ON_PAGE + 1;
        }
    }


    @Override
    protected String getDaoType() {
        return DaoHelper.VACANCY;
    }

}
