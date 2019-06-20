package com.epam.cafe.query;

import com.epam.cafe.api.query.QueryBuilderWithElementsNumber;

public class SelectIDInQueryBuilder implements QueryBuilderWithElementsNumber {

    @Override
    public String build(String tableName, int elementsNumber) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(tableName);
        query.append(" WHERE ID IN (");

        int lastID = elementsNumber - 1;
        for (int i = 0; i < lastID; i++) {
            query.append("?, ");
        }
        query.append("?)");

        return query.toString();
    }
}
