package com.epam.web.dao;

import com.epam.web.entity.MultipleId;


import java.util.Optional;

public interface MultipleIdDao<T extends MultipleId> {

    Optional<T> getById(Long userId, Long vacancyId) throws DaoException;

    void removeById(Long userId, Long vacancyId) throws DaoException;
}
