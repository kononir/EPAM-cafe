package com.epam.cafe.repository.specification.choosen.dishes;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.Dish;

import java.util.Collections;
import java.util.List;

public class ChosenDishesByOrderIDSpecification implements EntitySpecification<Dish>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM order_dishes WHERE OrderID = ?";

    private int orderID;

    public ChosenDishesByOrderIDSpecification(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public boolean specified(Dish dish) {
        return (dish.getID() == orderID);
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(orderID);
    }
}
