package com.epam.cafe.query;

import com.epam.cafe.api.QueryBuilder;

import java.util.List;

public class DeleteQueryBuilder implements QueryBuilder {

    @Override
    public String build(String tableName, List<String> params) {
        StringBuilder builder = new StringBuilder("DELETE FROM ");
        builder.append(tableName);
        builder.append("WHERE ");

        int last = params.size() - 1;
        for (int i = 0; i < last; i++) {
            builder.append(params.get(i));
            builder.append(" = ?, ");
        }
        builder.append(params.get(last));
        builder.append(" = ?");

        return builder.toString();
    }
}
