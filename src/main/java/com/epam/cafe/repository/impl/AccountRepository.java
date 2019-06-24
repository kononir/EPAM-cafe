package com.epam.cafe.repository.impl;

import com.epam.cafe.api.repository.specification.SqlSpecification;
import com.epam.cafe.entitie.builder.AccountBuilder;
import com.epam.cafe.entitie.Account;
import com.epam.cafe.repository.RepositoryException;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AccountRepository extends AbstractRepository<Account> {
    private static final String TABLE_NAME = "account";

    public AccountRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Map<String, Object> getParams(Account account) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(Account.ID_COLUMN, account.getID());
        params.put(Account.MONEY_COLUMN, account.getMoney());

        return params;
    }

    @Override
    public List<Account> query(SqlSpecification specification) throws RepositoryException {
        return executeQuery(specification, new AccountBuilder());
    }
}
