package com.epam.web.service;

import com.epam.web.dao.Dao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.entity.Identifiable;
import com.epam.web.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends Identifiable> implements Service<T> {

    static final Logger LOGGER = LogManager.getLogger();
    private final DaoHelperFactory daoHelperFactory;
    private final Validator validator;
    private final String daoType;

    public AbstractService(DaoHelperFactory daoHelperFactory, Validator validator, String daoType) {
        this.daoHelperFactory = daoHelperFactory;
        this.validator = validator;
        this.daoType = daoType;
    }

    public List<T> getAll() throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            Dao<T> dao = helper.getDao(daoType);
            return dao.getAll();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public T getById(Long id) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            Dao<T> dao = helper.getDao(daoType);
            Optional<T> item = dao.getById(id);
            if (item.isPresent()) {
                return item.get();
            } else {
                throw new ServiceException("Item not found");
            }
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ServiceException(e);
        }
    }

    public void update(T item) throws ServiceException {
        if (validator.validate(item)) {
            try (DaoHelper helper = getDaoHelperFactory().create()) {
                Dao<T> dao = helper.getDao(daoType);
                dao.save(item);
            } catch (DaoException e) {
                LOGGER.error(e.getMessage(),e);
                throw new ServiceException(e.getMessage(), e);
            }
        } else {
            throw new ServiceException("Validation failed");
        }
    }

    public void deleteById(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            Dao<T> dao = helper.getDao(daoType);
            dao.removeById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(),e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public String getDaoType() {
        return daoType;
    }

    protected DaoHelperFactory getDaoHelperFactory() {
        return daoHelperFactory;
    }

    protected Validator<T> getValidator() {
        return validator;
    }
}
