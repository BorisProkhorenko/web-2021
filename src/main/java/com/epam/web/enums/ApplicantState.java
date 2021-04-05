package com.epam.web.enums;


public enum ApplicantState{

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


    public static ApplicantState fromString(String text) throws EnumParsingException {
        for (ApplicantState state : ApplicantState.values()) {
            if (state.value.equalsIgnoreCase(text)) {
                return state;
            }
        }
        throw new EnumParsingException("Unknown state");
    }
}
