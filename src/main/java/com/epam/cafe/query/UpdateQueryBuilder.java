package com.epam.cafe.query;

import com.epam.cafe.api.QueryBuilder;

import java.util.List;

public class UpdateQueryBuilder implements QueryBuilder {
    private static final String ID_COLUMN = "ID";

    @Override
    public String build(String tableName, List<String> params) {
        StringBuilder builder = new StringBuilder("UPDATE ");
        builder.append(tableName);
        builder.append(" SET ");

        int last = params.size() - 1;
        for (int i = 0; i < last; i++) {
            builder.append(params.get(i));
            builder.append(" = ?, ");
        }
        builder.append(params.get(last));
        builder.append(" = ? WHERE ");
        builder.append(ID_COLUMN);
        builder.append(" = ?");

        return builder.toString();
    }
}
