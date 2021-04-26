package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.User;


import java.util.List;
import java.util.Optional;

public class UserService extends AbstractService<User> {

    public UserService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, User.TABLE_NAME);
    }

    protected UserService(DaoHelperFactory daoHelperFactory, String daoType) {
        super(daoHelperFactory, daoType);
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            UserDao userDao = (UserDao) helper.createDao(getDaoType());
            List<User> userList = userDao.getAllSorted();
            return userList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void changeBlock(Long id, boolean isBlocked) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            UserDao userDao = (UserDao) helper.createDao(getDaoType());
            isBlocked = !isBlocked;
            userDao.setUserBlockById(id, isBlocked);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> login(String username, String password) throws ServiceException {

        try (DaoHelper helper = getDaoHelperFactory().create()) {
            UserDao userDao = (UserDao) helper.createDao(getDaoType());
            Optional<User> user = userDao.findUserByUsernameAndPassword(username, password);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

}
