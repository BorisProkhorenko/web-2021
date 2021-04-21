package com.epam.web.service;

import com.epam.web.dao.*;

import com.epam.web.entity.Vacancy;

import java.util.List;

public class VacancyService extends AbstractService<Vacancy> {

    private final static int vacanciesOnPage = 2;

    public VacancyService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, Vacancy.TABLE_NAME);
    }


    public List<Vacancy> getVacanciesByPage(int page) throws ServiceException {
        page--;
        int skipped = page * vacanciesOnPage;
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            VacancyDao dao = (VacancyDao) helper.createDao(getDaoType());
            List<Vacancy> items = dao.getWithLimit(skipped, vacanciesOnPage);
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public int getVacanciesOnPage() {
        return vacanciesOnPage;
    }

}
