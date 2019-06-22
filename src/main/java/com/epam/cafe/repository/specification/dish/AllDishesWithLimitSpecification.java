package com.epam.cafe.repository.specification.dish;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.Dish;

import java.util.Arrays;
import java.util.List;

public class AllDishesWithLimitSpecification implements EntitySpecification<Dish>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM dish LIMIT ?,?";

    private int skipRecordsCount;
    private int recordsCount;

    public AllDishesWithLimitSpecification(int skipRecordsCount, int recordsCount) {
        this.skipRecordsCount = skipRecordsCount;
        this.recordsCount = recordsCount;
    }

    @Override
    public boolean specified(Dish specifiedElement) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Arrays.asList(skipRecordsCount, recordsCount);
    }
}
