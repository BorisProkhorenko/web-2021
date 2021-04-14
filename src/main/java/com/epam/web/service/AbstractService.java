package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.Identifiable;


import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends Identifiable> {

    protected final DaoHelperFactory daoHelperFactory;

    public AbstractService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public List<T> getAll() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            AbstractDao<T> dao = helper.createDao(getDaoType());
            List<T> items = dao.getAll();
            helper.endTransaction();
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public T getById(Long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            AbstractDao<T> dao = helper.createDao(getDaoType());
            Optional<T> item = dao.getById(id);
            helper.endTransaction();
            if (item.isPresent()) {
                return item.get();
            } else {
                throw new ServiceException("Item not found");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    protected abstract String getDaoType();
}
