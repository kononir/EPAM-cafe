package com.epam.cafe.repository.specification.order;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.order.Order;

import java.util.Arrays;
import java.util.List;

public class CurrentOrderByUserIDWithLimitSpecification implements SqlSpecification, EntitySpecification<Order> {
    private static final String QUERY
            = "SELECT * FROM `order` WHERE UserID = ? AND ReceiptTime > NOW() LIMIT ?,?";

    private int userID;

    private int skipRecordsCount;
    private int recordsCount;

    public CurrentOrderByUserIDWithLimitSpecification(int userID, int skipRecordsCount, int recordsCount) {
        this.userID = userID;
        this.skipRecordsCount = skipRecordsCount;
        this.recordsCount = recordsCount;
    }

    @Override
    public boolean specified(Order specifiedElement) {
        return false;
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Arrays.asList(userID, skipRecordsCount, recordsCount);
    }
}
