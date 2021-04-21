package com.epam.web.service;


import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;

import com.epam.web.dao.DaoHelperFactory;
import com.epam.web.dao.ResponseDao;
import com.epam.web.entity.Response;


import java.util.List;


public class ResponseService extends AbstractService<Response> {

    public ResponseService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, Response.TABLE_NAME);
    }

    public List<Response> getResponsesByUserId(Long id) throws ServiceException {
        try (DaoHelper helper = getDaoHelperFactory().create()) {
            ResponseDao dao = (ResponseDao) helper.createDao(getDaoType());
            List<Response> items = dao.getByUserId(id);
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
