package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.User;


import java.util.List;
import java.util.Optional;

public class UserService extends AbstractService<User> {

    public Optional<User> login(String username, String password) throws ServiceException {

        try (DaoHelper helper = getDaoHelperFactory().create()) {
            UserDao userDao = (UserDao) helper.createDao(getDaoType());
            Optional<User> user = userDao.findUserByUsernameAndPassword(username, password);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    public List<User> getUsers() throws ServiceException {
        return super.getAll();
    }

    @Override
    protected String getDaoType() {
        return User.TABLE_NAME;
    }
}
