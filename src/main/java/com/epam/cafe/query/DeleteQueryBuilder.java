package com.epam.cafe.query;

import com.epam.cafe.api.builder.QueryBuilder;

import java.util.List;

public class DeleteQueryBuilder implements QueryBuilder {

    @Override
    public String build(String tableName, List<String> params) {
        return "DELETE FROM " + tableName + "WHERE ID = ?";
    }
}
