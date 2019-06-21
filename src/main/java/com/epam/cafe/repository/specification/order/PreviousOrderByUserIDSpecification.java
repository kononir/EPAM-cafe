package com.epam.cafe.repository.specification.order;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.Dish;

import java.util.Collections;
import java.util.List;

public class PreviousOrderByUserIDSpecification implements EntitySpecification<Dish>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM `order` WHERE UserID = ? AND ReceiptTime <= NOW()";

    private int userID;

    public PreviousOrderByUserIDSpecification(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean specified(Dish specifiedElement) {
        return false;
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(userID);
    }
}
