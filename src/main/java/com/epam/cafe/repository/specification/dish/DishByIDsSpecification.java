package com.epam.cafe.repository.specification.dish;

import com.epam.cafe.api.query.QueryBuilderWithElementsNumber;
import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.query.SelectIDInQueryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DishByIDsSpecification implements EntitySpecification<Dish>, SqlSpecification {
    private static final String TABLE_NAME = "dish";

    private List<Integer> dishIDs;

    public DishByIDsSpecification(List<Integer> dishIDs) {
        this.dishIDs = dishIDs;
    }

    @Override
    public boolean specified(Dish specifiedElement) {
        return dishIDs.contains(specifiedElement.getID());
    }

    @Override
    public String toSqlClause() {
        QueryBuilderWithElementsNumber queryBuilder = new SelectIDInQueryBuilder();
        return queryBuilder.build(TABLE_NAME, dishIDs.size());
    }

    @Override
    public List<Object> getParams() {
        return new ArrayList<>(dishIDs);
    }
}
