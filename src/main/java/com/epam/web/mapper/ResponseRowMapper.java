package com.epam.web.mapper;

import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.RecruitingProcessDao;
import com.epam.web.entity.RecruitingProcess;
import com.epam.web.entity.Response;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class ResponseRowMapper implements RowMapper<Response> {

    private final static String ID = "id";
    private final static String SUBJECT = "subject";
    private final static String DETAILS = "details";
    private final static String DATE = "date";
    private final static String PROCESS_ID = "user_vacancy_id";
    private final RecruitingProcessDao recruitingProcessDao = RecruitingProcessDao.getInstance();

    public ResponseRowMapper() {
        if (recruitingProcessDao.getConnection() == null){
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            recruitingProcessDao.setConnection(connection);
        }
    }

    @Override
    public Response map(ResultSet resultSet) throws SQLException, DaoException {
        Long id = resultSet.getLong(ID);
        String subject = resultSet.getString(SUBJECT);
        String details = resultSet.getString(DETAILS);
        Date date = resultSet.getTimestamp(DATE);
        Long recruitingProcessId = resultSet.getLong(PROCESS_ID);
        Optional<RecruitingProcess> optionalProcess = recruitingProcessDao.getById(recruitingProcessId);
        if (optionalProcess.isPresent()) {
            RecruitingProcess process = optionalProcess.get();
            return new Response(id, subject, details, date, process);
        } else {
            throw new DaoException("empty user_vacancy");
        }


    }


}
