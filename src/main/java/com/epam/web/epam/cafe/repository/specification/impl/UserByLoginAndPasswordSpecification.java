package com.epam.web.epam.cafe.repository.specification.impl;

import com.epam.web.epam.cafe.entitie.user.User;
import com.epam.web.epam.cafe.repository.exception.SqlConvertingException;
import com.epam.web.epam.cafe.repository.specification.GeneralSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserByLoginAndPasswordSpecification implements GeneralSpecification<User> {
    private String desiredLogin;
    private String desiredPassword;

    private static final int FIRST = 1;
    private static final int SECOND = 2;

    private static final String QUERY = "SELECT * FROM user " +
            "LEFT JOIN account ON user.AccountID = account.ID " +
            "WHERE Login = ? AND Password = ?";

    public UserByLoginAndPasswordSpecification(String desiredLogin, String desiredPassword) {
        this.desiredLogin = desiredLogin;
        this.desiredPassword = desiredPassword;
    }

    @Override
    public boolean specified(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        return (desiredLogin.equals(login) && desiredPassword.equals(password));
    }

    @Override
    public PreparedStatement toSqlClause(Connection connection) throws SqlConvertingException {
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(QUERY);
            statement.setString(FIRST, desiredLogin);
            statement.setString(SECOND, desiredPassword);
        } catch (SQLException e) {
            throw new SqlConvertingException("Converting to Login & Password specification error.", e);
        }

        return statement;
    }
}
