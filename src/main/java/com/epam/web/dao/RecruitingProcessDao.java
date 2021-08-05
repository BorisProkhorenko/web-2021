package com.epam.web.dao;

import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.User;
import com.epam.web.entity.Vacancy;
import com.epam.web.enums.ApplicantState;
import com.epam.web.mapper.RecruitingProcessRowMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class RecruitingProcessDao extends AbstractDao<RecruitingProcess> {

    private final static AtomicReference<RecruitingProcessDao> INSTANCE = new AtomicReference<>();
    private final static Lock INSTANCE_LOCK = new ReentrantLock();

    private final static String INSERT_QUERY = "INSERT INTO USER_VACANCY(user_id, vacancy_id, state," +
            " rating) VALUES(?,?,?,?);";
    private final static String UPDATE_QUERY = "UPDATE USER_VACANCY SET state=?, rating=? " +
            "WHERE id=?;";

    private final static String SELECT_BY_VACANCY = "SELECT * FROM USER_VACANCY WHERE vacancy_id=?";

    private final static String SELECT_BY_USER_AND_VACANCY = "SELECT * FROM USER_VACANCY WHERE user_id=?" +
            " AND vacancy_id=?";

    private final static String DELETE_VACANCY_LINK = "UPDATE USER_VACANCY SET vacancy_id=NULL WHERE vacancy_id=?";

    public RecruitingProcessDao() {
        super(new RecruitingProcessRowMapper());
    }

    public static RecruitingProcessDao getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE_LOCK.lock();

            try {
                if (INSTANCE.get() == null) {
                    RecruitingProcessDao dao = new RecruitingProcessDao();
                    INSTANCE.getAndSet(dao);
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    @Override
    protected List<Object> extractParams(RecruitingProcess item) {
        ApplicantState state = item.getState();
        return Arrays.asList(
                state.toString(),
                item.getRating());
    }

    @Override
    public void save(RecruitingProcess item) throws DaoException {
        ArrayList<Object> paramList = new ArrayList<>(extractParams(item));
        Long id = item.getId();
        User user = item.getUser();
        Long userId = user.getId();
        Vacancy vacancy = item.getVacancy();
        Long vacancyId = vacancy.getId();
        String query;
        if (getById(id).isPresent()) {
            paramList.add(id);
            query = getUpdateQuery();
        } else {
            paramList.add(0, userId);
            paramList.add(1, vacancyId);
            query = getInsertQuery();
        }
        Object[] params = paramList.toArray();
        executeUpdate(query, params);
    }

    public List<RecruitingProcess> getByVacancyId(Long id) throws DaoException {
        return executeQuery(SELECT_BY_VACANCY, id);
    }

    public List<RecruitingProcess> getByUserAndVacancyId(Long userId, Long vacancyId) throws DaoException {
        return executeQuery(SELECT_BY_USER_AND_VACANCY, userId, vacancyId);
    }

    public void deleteVacancyLink(Long id) throws DaoException {
        executeUpdate(DELETE_VACANCY_LINK, id);
    }

    @Override
    protected String getTableName() {
        return RecruitingProcess.TABLE_NAME;
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
