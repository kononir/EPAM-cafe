package com.epam.cafe.repository.specification.user;

import com.epam.cafe.api.query.QueryBuilderWithElementsNumber;
import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.query.SelectIDInQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class UserByIDsSpecification implements SqlSpecification, EntitySpecification<User> {
    private static final String TABLE_NAME = "user";

    private List<Integer> userIDs;

    public UserByIDsSpecification(List<Integer> userIDs) {
        this.userIDs = userIDs;
    }

    @Override
    public boolean specified(User specifiedElement) {
        return userIDs.contains(specifiedElement.getID());
    }

    @Override
    public String toSqlClause() {
        QueryBuilderWithElementsNumber queryBuilder = new SelectIDInQueryBuilder();
        return queryBuilder.build(TABLE_NAME, userIDs.size());
    }

    @Override
    public List<Object> getParams() {
        return new ArrayList<>(userIDs);
    }
}
