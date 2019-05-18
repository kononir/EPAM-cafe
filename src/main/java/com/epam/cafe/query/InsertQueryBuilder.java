package com.epam.cafe.query;

import com.epam.cafe.api.builder.QueryBuilder;

import java.util.List;

public class InsertQueryBuilder implements QueryBuilder {

    @Override
    public String build(String tableName, List<String> params) {
        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(tableName);
        builder.append(" (");

        int last = params.size() - 1;
        for (int i = 0; i < last; i++) {
            builder.append(params.get(i));
        }
        builder.append(params.get(last));

        builder.append(") VALUES(");
        for (int i = 0; i < last; i++) {
            builder.append("?, ");
        }
        builder.append("?)");

        return builder.toString();
    }
}
