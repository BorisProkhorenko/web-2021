package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.Applicant;
import com.epam.web.entity.Identifiable;


import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends Identifiable> {

    private final DaoHelperFactory daoHelperFactory;

    public AbstractService(){
        daoHelperFactory = new DaoHelperFactory();
    }

    public List<T> getAll() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AbstractDao<T> dao = helper.createDao(getDaoType());
            List<T> items = dao.getAll();
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public T getById(Long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AbstractDao<T> dao = helper.createDao(getDaoType());
            Optional<T> item = dao.getById(id);
            if (item.isPresent()) {
                return item.get();
            } else {
                throw new ServiceException("Item not found");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void update(T item) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            AbstractDao<T> dao = helper.createDao(getDaoType());
            dao.save(item);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void deleteById(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            AbstractDao<T> dao = helper.createDao(getDaoType());
            dao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    protected abstract String getDaoType();

    protected DaoHelperFactory getDaoHelperFactory(){
        return daoHelperFactory;
    }
}
