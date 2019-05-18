package com.epam.cafe.api.repository.specification;

import java.util.List;

public interface SqlSpecification {
    String toSqlClause();
    List<Object> getParams();
}
