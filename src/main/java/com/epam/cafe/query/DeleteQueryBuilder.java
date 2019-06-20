package com.epam.cafe.query;

import com.epam.cafe.api.query.QueryBuilder;

public class DeleteQueryBuilder implements QueryBuilder {

    @Override
    public String build(String tableName) {
        return "DELETE FROM " + tableName + " WHERE ID = ?";
    }
}
