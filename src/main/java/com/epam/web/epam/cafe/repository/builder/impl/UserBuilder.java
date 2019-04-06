package com.epam.web.epam.cafe.repository.builder.impl;

import com.epam.web.epam.cafe.entitie.Account;
import com.epam.web.epam.cafe.entitie.user.User;
import com.epam.web.epam.cafe.entitie.user.UserRole;
import com.epam.web.epam.cafe.repository.builder.EntityBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserBuilder implements EntityBuilder<User> {

    @Override
    public List<User> build(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String login = resultSet.getString("Login");
            String password = resultSet.getString("Password");
            String name = resultSet.getString("Name");
            String surname = resultSet.getString("Surname");
            UserRole role = UserRole.valueOf(resultSet.getString("Role"));
            Integer score = resultSet.getInt("Score");

            Account account;
            Integer accountID = resultSet.getInt("AccountID");
            if (accountID != null) {
                account = new Account(accountID, resultSet.getBigDecimal("Money"));
            } else {
                account = null;
            }

            users.add(new User(ID, login, password, name, surname, score, role, account));
        }

        return users;
    }
}
