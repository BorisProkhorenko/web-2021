package com.epam.web.extractor;

import com.epam.web.entity.Identifiable;

import java.util.List;

public interface StatementExtractor<T extends Identifiable> {

    List<Object> extractParamsForInsertQuery(T item);
    List<Object> extractParamsForUpdateQuery(T item);

    String getInsertQuery();
    String getUpdateQuery();

}
