package com.epam.web.dao;


import com.epam.web.entity.Vacancy;
import com.epam.web.mapper.VacancyRowMapper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class VacancyDao extends AbstractDao<Vacancy> {

    private final static AtomicReference<VacancyDao> INSTANCE = new AtomicReference<>();
    private final static Lock INSTANCE_LOCK = new ReentrantLock();

    private final static String INSERT_QUERY = "INSERT INTO VACANCY(name, salary, responsibility, description, " +
            "requirements) VALUES(?,?,?,?,?);";

    private final static String UPDATE_QUERY = "UPDATE VACANCY SET name=?, salary=?, responsibility=?, description=?, " +
            "requirements=? WHERE id=?;";


    private VacancyDao() {
        super(new VacancyRowMapper());
    }

    public static VacancyDao getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE_LOCK.lock();

            try {
                if (INSTANCE.get() == null) {
                    VacancyDao dao = new VacancyDao();
                    INSTANCE.getAndSet(dao);
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    @Override
    protected List<Object> extractParams(Vacancy item) {
        return Arrays.asList(
                item.getName(),
                item.getSalary(),
                item.getResponsibility(),
                item.getDescription(),
                item.getRequirements());
    }

    @Override
    protected String getTableName() {
        return Vacancy.TABLE_NAME;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_QUERY;
    }
}
