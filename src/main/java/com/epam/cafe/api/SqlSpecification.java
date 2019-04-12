package com.epam.cafe.api;

import java.util.List;

public interface SqlSpecification {
    String toSqlClause();
    List<Object> getParams();
}
