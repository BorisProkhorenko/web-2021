package com.epam.web.validator;

import com.epam.web.entity.Identifiable;

public interface Validator<T extends Identifiable> {

    boolean validate(T item);
}
