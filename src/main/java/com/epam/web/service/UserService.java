package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;


import java.util.List;
import java.util.Optional;

public class UserService extends AbstractService<User> {

    public Optional<User> login(String username, String password) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
          helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> user = userDao.findUserByUsernameAndPassword(username, password);
            helper.endTransaction();
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
        return DaoHelper.USER;
    }
}
