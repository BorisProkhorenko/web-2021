package com.epam.web.enums;

import java.util.Optional;

public enum ApplicantState {

    NEW("New"),
    PRELIMINARY("Preliminary"),
    TECHNICAL("Technical"),
    HIRED("Hired"),
    REJECTED("Rejected");

    private final String value;

    ApplicantState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<ApplicantState> fromString(String text) {
        for (ApplicantState state : ApplicantState.values()) {
            if (state.value.equalsIgnoreCase(text)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }
}
