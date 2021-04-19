package com.epam.web.entity;

import com.epam.web.enums.ApplicantState;

public class RecruitingProcess implements MultipleId{

    public final static String TABLE_NAME = "user_vacancy";

    private final Long userId;
    private final Long vacancyId;
    private final ApplicantState state;
    private final Integer preliminaryPoints;


    public RecruitingProcess(Long userId, Long vacancyId, ApplicantState state, Integer preliminaryPoints) {
        this.userId = userId;
        this.vacancyId = vacancyId;
        this.state = state;
        this.preliminaryPoints = preliminaryPoints;

    }

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    public Long getUserId() {
        return userId;
    }
    @Override
    public Long getVacancyId() {
        return vacancyId;
    }


    public ApplicantState getState() {
        return state;
    }

    public Integer getPreliminaryPoints() {
        return preliminaryPoints;
    }

}
