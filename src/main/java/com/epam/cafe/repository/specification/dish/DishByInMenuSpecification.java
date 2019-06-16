package com.epam.cafe.repository.specification.dish;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.dish.Dish;

import java.util.Collections;
import java.util.List;

public class DishByInMenuSpecification implements EntitySpecification<Dish>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM dish WHERE IsInMenu = ?";

    private boolean inMenu;

    public DishByInMenuSpecification(boolean inMenu) {
        this.inMenu = inMenu;
    }

    @Override
    public boolean specified(Dish specifiedElement) {
        return specifiedElement.isInMenu() == inMenu;
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(inMenu);
    }
}
