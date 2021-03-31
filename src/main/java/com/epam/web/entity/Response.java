package com.epam.web.entity;

public class Response implements Identifiable {

    private final Long id;
    private final String main;
    private final String details;
    private final String date;
    private final Long userId;
    private final Long vacancyId;

    public Response(Long id, String main, String details, String date, Long userId, Long vacancyId) {
        this.id = id;
        this.main = main;
        this.details = details;
        this.date = date;
        this.userId = userId;
        this.vacancyId = vacancyId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getVacancyId() {
        return vacancyId;
    }
}
