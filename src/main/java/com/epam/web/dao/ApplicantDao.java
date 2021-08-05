package com.epam.web.dao;

import com.epam.web.entity.Applicant;
import com.epam.web.entity.User;
import com.epam.web.enums.Gender;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ApplicantDao extends UserDao {

    private final static AtomicReference<ApplicantDao> INSTANCE = new AtomicReference<>();
    private final static Lock INSTANCE_LOCK = new ReentrantLock();

    private final static String UPDATE_QUERY = "UPDATE USER SET name=?, gender=?, age=?, contacts=?, " +
            "education=?, experience=?, skills=? WHERE id=?";

    private final static String UPDATE_PHOTO = "UPDATE USER SET photo=? WHERE id=?";



    private ApplicantDao() {
        super();
    }

    public static ApplicantDao getInstance() {
        if (INSTANCE.get() == null) {
            INSTANCE_LOCK.lock();

            try {
                if (INSTANCE.get() == null) {
                    ApplicantDao dao = new ApplicantDao();
                    INSTANCE.getAndSet(dao);
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    @Override
    protected List<Object> extractParams(User item) {
        Applicant applicant = (Applicant) item;
        Gender gender = applicant.getGender();
        return Arrays.asList(
                applicant.getName(),
                gender.toString(),
                applicant.getAge(),
                applicant.getContacts(),
                applicant.getEducation(),
                applicant.getExperience(),
                applicant.getSkills());
    }

    public void updatePhoto(String photo, Long id) throws DaoException {
        executeUpdate(UPDATE_PHOTO, photo, id);
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_QUERY;
    }
}
