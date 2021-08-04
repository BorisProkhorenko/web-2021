package com.epam.web.service;


import com.epam.web.dao.DaoException;
import com.epam.web.entity.Identifiable;

import java.util.List;


/**
 * This Service Interface represents the contract(CRUD), which all of Service classes have to follow.
 * Uses {@link com.epam.web.validator.Validator} to validate Objects.
 * Uses {@link com.epam.web.dao.Dao} to get access to Data Base.
 *
 * @param <T> must be an implementation of {@link Identifiable} interface
 * @author Boris Prokhorenko
 * @see Identifiable
 * @see com.epam.web.validator.Validator}
 * @see com.epam.web.dao.Dao
 */
public interface Service<T extends Identifiable> {


    /**
     * Method allows to get all from Data Base table using DAO Layer
     *
     * @return List of {@link Identifiable} objects from DAO Layer
     * @throws ServiceException instead {@link DaoException}
     */
    List<T> getAll() throws ServiceException;

    /**
     * Method allows to get {@link Identifiable} object from DAO Layer by it's
     *
     * @param id - primary key for all {@link Identifiable}
     * @return Optional of {@link Identifiable} object
     * @throws ServiceException instead {@link DaoException}
     */
    T getById(Long id) throws ServiceException;

    /**
     * Method update object data in Data Base using DAO Layer.
     *
     * @param item implements ({@link Identifiable})
     * @throws ServiceException instead {@link DaoException}
     */
    void update(T item) throws ServiceException;

    /**
     * Remove row from Data Base table by
     *
     * @param id - primary key for all {@link Identifiable} using DAO Layer
     * @throws ServiceException instead {@link DaoException}
     */
    void deleteById(Long id) throws ServiceException;
}
