package com.epam.cafe.repository.specification.user;

import com.epam.cafe.api.repository.specification.EntitySpecification;
import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.entitie.user.UserRole;

import java.util.Collections;
import java.util.List;

public class UserByRoleSpecification implements EntitySpecification<User>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM user WHERE Role = ?";

    private UserRole desiredRole;

    public UserByRoleSpecification(UserRole desiredRole) {
        this.desiredRole = desiredRole;
    }

    @Override
    public boolean specified(User specifiedElement) {
        UserRole role = specifiedElement.getRole();
        return desiredRole.equals(role);
    }

    @Override
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Collections.singletonList(desiredRole.name());
    }
}
