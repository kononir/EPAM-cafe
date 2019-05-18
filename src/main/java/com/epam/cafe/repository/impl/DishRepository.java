package com.epam.cafe.repository.impl;

import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.builder.DishBuilder;
import com.epam.cafe.entitie.Dish;
import com.epam.cafe.repository.exception.RepositoryException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DishRepository extends AbstractRepository<Dish> {
    private static final String TABLE_NAME = "dish";

    public DishRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Dish> query(SqlSpecification specification) throws RepositoryException {
        return executeQuery(specification, new DishBuilder());
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParams(Dish dish) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(Dish.ID_COLUMN, dish.getID());
        params.put(Dish.NAME_COLUMN, dish.getName());
        params.put(Dish.DESCRIPTION_COLUMN, dish.getDescription());
        params.put(Dish.COST_COLUMN, dish.getCost());
        params.put(Dish.IMAGE_HREF_COLUMN, dish.getImageHref());
        params.put(Dish.IS_IN_MENU_COLUMN, dish.isInMenu());

        return params;
    }
}
