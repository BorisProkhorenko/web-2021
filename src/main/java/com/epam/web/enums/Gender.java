package com.epam.web.enums;

import java.util.Optional;

public enum Gender {

    MALE("male"),
    FEMALE("female");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<Gender> fromString(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.value.equalsIgnoreCase(text)) {
                return Optional.of(gender);
            }
        }
        return Optional.empty();
    }
}
