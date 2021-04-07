package com.epam.web.service;

import com.epam.web.dao.*;

import com.epam.web.entity.Vacancy;

import java.util.List;

public class VacancyService extends AbstractService<Vacancy> {


    public List<Vacancy> getVacancies() throws ServiceException {
        return super.getAll();
    }

    @Override
    protected String getDaoType() {
        return DaoHelper.VACANCY;
    }
}
