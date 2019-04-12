package com.epam.cafe.api;

import java.util.List;

public interface QueryBuilder {
    String build(String tableName, List<String> params);
}
