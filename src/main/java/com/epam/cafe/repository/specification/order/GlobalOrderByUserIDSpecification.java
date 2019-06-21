package com.epam.cafe.repository.specification.order;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.order.Order;

import java.util.Collections;
import java.util.List;

public class GlobalOrderByUserIDSpecification implements SqlSpecification, EntitySpecification<Order> {
    private static final String QUERY = "SELECT * FROM `order` WHERE UserID != ? AND ReceiptTime <= NOW()";

    private int userID;

    public GlobalOrderByUserIDSpecification(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean specified(Order specifiedElement) {
        return (specifiedElement.getID() != userID);
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
