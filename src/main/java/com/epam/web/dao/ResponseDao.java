package com.epam.web.dao;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;
import com.epam.web.mapper.ResponseRowMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResponseDao extends AbstractDao<Response> {

    private final static AtomicReference<ResponseDao> INSTANCE = new AtomicReference<>();
    private final static Lock INSTANCE_LOCK = new ReentrantLock();

    private final static String INSERT_QUERY = "INSERT INTO RESPONSE(subject, details, " +
            "user_vacancy_id) VALUES(?,?,?);";

    private final static String UPDATE_QUERY = "UPDATE RESPONSE SET subject=?, details=? WHERE id=?;";

    private final static String SELECT_BY_PROCESS_ID = "SELECT * FROM response WHERE user_vacancy_id=?;";

    private final static String SELECT_BY_USER_ID = "SELECT * FROM response JOIN user_vacancy ON" +
            " user_vacancy.user_id=? AND response.user_vacancy_id=user_vacancy.id ORDER BY DATE DESC;";

    private final static String DELETE_BY_VACANCY_ID = "DELETE response FROM response INNER JOIN user_vacancy" +
            " WHERE response.user_vacancy_id=user_vacancy.id AND user_vacancy.vacancy_id=?";


    public ResponseDao() {

        super(new ResponseRowMapper());
    }

    public static ResponseDao getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE_LOCK.lock();

            try {
                if (INSTANCE.get() == null) {
                    ResponseDao dao = new ResponseDao();
                    INSTANCE.getAndSet(dao);
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }


    public List<Response> getByUserId(Long id) throws DaoException {
        return executeQuery(SELECT_BY_USER_ID, id);
    }

    public List<Response> getByProcessId(Long id) throws DaoException {
        return executeQuery(SELECT_BY_PROCESS_ID, id);
    }

    @Override
    protected List<Object> extractParams(Response item) {
        return Arrays.asList(
                item.getSubject(),
                item.getDetails());
    }

    @Override
    public void save(Response item) throws DaoException {
        ArrayList<Object> paramList = new ArrayList<>(extractParams(item));
        Long id = item.getId();
        RecruitingProcess process = item.getRecruitingProcess();
        Long processId = process.getId();
        String query;
        if (getById(id).isPresent()) {
            paramList.add(id);
            query = getUpdateQuery();
        } else {
            paramList.add(processId);
            query = getInsertQuery();
        }
        Object[] params = paramList.toArray();
        executeUpdate(query, params);
    }


    public void deleteByVacancyId(Long id) throws DaoException {
        executeUpdate(DELETE_BY_VACANCY_ID, id);
    }

    @Override
    protected String getTableName() {
        return Response.TABLE_NAME;
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
