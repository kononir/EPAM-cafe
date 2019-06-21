package com.epam.cafe.repository.specification.bonus;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.Bonus;

import java.util.Collections;
import java.util.List;

public class LastBonusSpecification implements SqlSpecification, EntitySpecification<Bonus> {
    private static final String QUERY = "SELECT * FROM bonus WHERE ID = (SELECT MAX(ID) FROM bonus)";

    @Override
    public boolean specified(Bonus specifiedElement) {
        return false;
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.emptyList();
    }
}
