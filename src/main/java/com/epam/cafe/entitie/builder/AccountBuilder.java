package com.epam.cafe.entitie.builder;

import com.epam.cafe.api.builder.EntityBuilder;
import com.epam.cafe.entitie.Account;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBuilder implements EntityBuilder<Account> {

    @Override
    public Account build(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt(Account.ID_COLUMN);
        BigDecimal money = resultSet.getBigDecimal(Account.MONEY_COLUMN);

        return new Account(ID, money);
    }
}
