package com.epam.web.enums;

import java.util.Optional;

public enum Role {
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

    public static Optional<Role> fromString(String text) {
        for (Role role : Role.values()) {
            if (role.value.equalsIgnoreCase(text)) {
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }
}
