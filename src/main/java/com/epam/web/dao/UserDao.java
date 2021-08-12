package com.epam.web.dao;

import com.epam.web.entity.User;
import com.epam.web.enums.Role;
import com.epam.web.mapper.UserRowMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserDao extends AbstractDao<User> {

    private final static AtomicReference<UserDao> INSTANCE = new AtomicReference<>();
    private final static Lock INSTANCE_LOCK = new ReentrantLock();


    private final static String FIND_BY_USERNAME_AND_PASSWORD = "SELECT * FROM USER WHERE USERNAME = ?" +
            " AND PASSWORD = MD5(?)";

    private final static String INSERT_QUERY = "INSERT INTO USER(username, password, role, is_blocked)" +
            " VALUES(?,?,MD5(?),?);";

    private final static String UPDATE_QUERY = "UPDATE USER SET username=?, password=MD5(?), role=?, is_blocked=?" +
            "WHERE id=?;";

    private final static String BLOCK_QUERY = "UPDATE USER SET is_blocked=? WHERE id=?;";

    private final static String ORDER_BY_USERNAME = "ORDER BY username, role;";

    protected UserDao() {
        super(new UserRowMapper());
    }

    public static UserDao getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE_LOCK.lock();

            try {
                if (INSTANCE.get() == null) {
                    UserDao dao = new UserDao();
                    INSTANCE.getAndSet(dao);
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_USERNAME_AND_PASSWORD,
                username,
                password);
    }

    public void setUserBlockById(Long id, boolean isBlocked) throws DaoException {
        executeUpdate(BLOCK_QUERY, isBlocked, id);
    }

    @Override
    protected List<Object> extractParams(User item) {
        Role role = item.getRole();
        return Arrays.asList(
                item.getUsername(),
                item.getPassword(),
                role.toString(),
                item.getIsBlocked());
    }

    public List<User> getAllSorted() throws DaoException {
        return executeQuery(concatQuery(SELECT_ALL_FROM, ORDER_BY_USERNAME));
    }

    @Override
    protected String getTableName() {
        return User.TABLE_NAME;
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
