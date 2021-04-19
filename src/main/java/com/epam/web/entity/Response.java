package com.epam.web.entity;

import java.util.Date;

public class Response implements MultipleId {

    public final static String TABLE_NAME = "response";

    private final Long id;
    private final String subject;
    private final String details;
    private final Date date;
    private final Long userId;
    private final Long vacancyId;

    public Response(Long id, String subject, String details, Date date, Long userId, Long vacancyId) {
        this.id = id;
        this.subject = subject;
        this.details = details;
        this.date = date;
        this.userId = userId;
        this.vacancyId = vacancyId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Long getVacancyId() {
        return vacancyId;
    }
}
