package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.Identifiable;


import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends Identifiable> {

    private final DaoHelperFactory daoHelperFactory;
    private final String daoType;

    public AbstractService(DaoHelperFactory daoHelperFactory, String daoType){
        this.daoHelperFactory = daoHelperFactory;

        this.daoType = daoType;
    }

    public List<T> getAll() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AbstractDao<T> dao = helper.createDao(daoType);
            List<T> items = dao.getAll();
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public T getById(Long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            AbstractDao<T> dao = helper.createDao(daoType);
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
            AbstractDao<T> dao = helper.createDao(daoType);
            dao.save(item);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void deleteById(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            AbstractDao<T> dao = helper.createDao(daoType);
            dao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public String getDaoType() {
        return daoType;
    }

    protected DaoHelperFactory getDaoHelperFactory(){
        return daoHelperFactory;
    }
}
