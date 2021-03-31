package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private final DaoHelperFactory daoHelperFactory;

    public UserService() {
        daoHelperFactory = new DaoHelperFactory();
    }

    public Optional<User> login(String username, String password) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
          helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            Optional<User> user = userDao.findUserByUsernameAndPassword(username, password);
            helper.endTransaction();
            return user;
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }

    }


}
