package com.epam.cafe.repository.specification.bonus;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.Bonus;

import java.util.Arrays;
import java.util.List;

public class BonusByUserIDWithLimitSpecification implements EntitySpecification<Bonus>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM bonus WHERE UserID = ? LIMIT ?,?";

    private int desiredUserID;

    private int skipRecordsCount;
    private int recordsCount;

    public BonusByUserIDWithLimitSpecification(int desiredUserID, int skipRecordsCount, int recordsCount) {
        this.desiredUserID = desiredUserID;
        this.skipRecordsCount = skipRecordsCount;
        this.recordsCount = recordsCount;
    }

    @Override
    public boolean specified(Bonus specifiedElement) {
        return (specifiedElement.getUserID() == desiredUserID);
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Arrays.asList(desiredUserID, skipRecordsCount, recordsCount);
    }
}
