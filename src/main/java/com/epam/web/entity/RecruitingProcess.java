package com.epam.web.entity;

import com.epam.web.enums.ApplicantState;

public class RecruitingProcess implements Identifiable {

    private final Long userId;
    private final Long vacancyId;
    private final ApplicantState state;
    private final Integer preliminaryPoints;
    private final Integer technicalPoints;

    public RecruitingProcess(Long userId, Long vacancyId, ApplicantState state, Integer preliminaryPoints, Integer technicalPoints) {
        this.userId = userId;
        this.vacancyId = vacancyId;
        this.state = state;
        this.preliminaryPoints = preliminaryPoints;
        this.technicalPoints = technicalPoints;
    }

    @Override
    public Long getId() {
        return userId;
    }

    public Long getVacancyId() {
        return vacancyId;
    }

    public Long getUserId() {
        return userId;
    }

    public ApplicantState getState() {
        return state;
    }

    public Integer getPreliminaryPoints() {
        return preliminaryPoints;
    }

    public Integer getTechnicalPoints() {
        return technicalPoints;
    }
}
