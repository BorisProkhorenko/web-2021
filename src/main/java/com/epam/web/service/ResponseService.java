package com.epam.web.service;


import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;

import com.epam.web.dao.ResponseDao;
import com.epam.web.entity.Response;


import java.util.List;


public class ResponseService extends AbstractService<Response> {


    public List<Response> getResponses() throws ServiceException {
        return super.getAll();
    }

    public List<Response> getResponsesByUserId(Long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            ResponseDao dao = helper.createResponseDao();
            List<Response> items = dao.getByUserId(id);
            helper.endTransaction();
            return items;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    protected String getDaoType() {
        return DaoHelper.RESPONSE;
    }
}
