package com.epam.web.enums;


public enum Role  {
    ADMIN("Admin"),
    HR("HR"),
    APPLICANT("Applicant");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role fromString(String text) throws EnumParsingException {
        for (Role role : Role.values()) {
            if (role.value.equalsIgnoreCase(text)) {
                return role;
            }
        }
        throw new EnumParsingException("Unknown role");
    }
}
