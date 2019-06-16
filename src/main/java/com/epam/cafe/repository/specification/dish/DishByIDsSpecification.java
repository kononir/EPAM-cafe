package com.epam.cafe.repository.specification.dish;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.dish.Dish;

import java.util.Collections;
import java.util.List;

public class DishByIDsSpecification implements EntitySpecification<Dish>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM dish WHERE ID = ?";

    private Integer id;

    public DishByIDsSpecification(Integer id) {
        this.id = id;
    }

    @Override
    public boolean specified(Dish specifiedElement) {
        return id.equals(specifiedElement.getID());
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(id);
    }
}
