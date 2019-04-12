package com.epam.cafe.repository.impl;

import com.epam.cafe.builder.UserBuilder;
import com.epam.cafe.api.GeneralSpecification;
import com.epam.cafe.api.Repository;
import com.epam.cafe.entitie.Account;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.repository.exception.RepositoryException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRepository extends AbstractRepository<User> implements Repository<User> {
    private static final String TABLE_NAME = "user";

    public UserRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> query(GeneralSpecification<User> entitySpecification) throws RepositoryException {
        return executeQuery(entitySpecification, new UserBuilder());
    }

    @Override
    protected Map<String, Object> getParams(User user) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(User.ID_COLUMN, user.getID());
        params.put(User.LOGIN_COLUMN, user.getLogin());
        params.put(User.PASSWORD_COLUMN, user.getPassword());
        params.put(User.NAME_COLUMN, user.getName());
        params.put(User.SURNAME_COLUMN, user.getSurname());
        params.put(User.ROLE_COLUMN, user.getRole());
        params.put(User.SCORE_COLUMN, user.getScore());

        Account account = user.getAccount();
        Integer accountID;
        if (account != null) {
            accountID = account.getID();
        } else {
            accountID = null;
        }

        params.put(User.ACCOUNT_ID_COLUMN, accountID);

        return params;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
