package com.epam.web.service;

import com.epam.web.dao.*;

import com.epam.web.entity.Vacancy;

import java.util.List;

public class VacancyService extends AbstractService<Vacancy> {

    private final int vacanciesOnPage = 2;


    public List<Vacancy> getVacanciesByPage(int page) throws ServiceException {
        page--;
        int skipped = page * vacanciesOnPage;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            VacancyDao dao = helper.createVacancyDao();
            List<Vacancy> items = dao.getWithLimit(skipped, vacanciesOnPage);
            helper.endTransaction();
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public int getVacanciesOnPage() {
        return vacanciesOnPage;
    }

    @Override
    protected String getDaoType() {
        return DaoHelper.VACANCY;
    }

}
