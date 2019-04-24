package com.epam.cafe.builder;

import com.epam.cafe.api.EntityBuilder;
import com.epam.cafe.entitie.Account;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.entitie.user.UserRole;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserBuilder implements EntityBuilder<User> {

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        int ID = resultSet.getInt(User.ID_COLUMN);
        String login = resultSet.getString(User.LOGIN_COLUMN);
        String password = resultSet.getString(User.PASSWORD_COLUMN);
        String name = resultSet.getString(User.NAME_COLUMN);
        String surname = resultSet.getString(User.SURNAME_COLUMN);
        UserRole role = UserRole.valueOf(resultSet.getString(User.ROLE_COLUMN));
        Object isBanned = resultSet.getObject(User.IS_BANNED_COLUMN);
        Object score = resultSet.getObject(User.SCORE_COLUMN);
        Object accountID = resultSet.getObject(User.ACCOUNT_ID_COLUMN);

        Account account;
        if (accountID != null) {
            BigDecimal money = resultSet.getBigDecimal("Money");
            account = new Account((Integer) accountID, money);
        } else {
            account = null;
        }

        return new User(ID, login, password, name, surname, role, (Boolean) isBanned, (Integer) score, account);
    }
}
