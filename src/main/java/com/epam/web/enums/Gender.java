package com.epam.web.enums;


public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static Gender fromString(String text) throws EnumParsingException {
        for (Gender gender : Gender.values()) {
            if (gender.value.equalsIgnoreCase(text)) {
                return gender;
            }
        }
        throw new EnumParsingException("Unknown gender");
    }
}
