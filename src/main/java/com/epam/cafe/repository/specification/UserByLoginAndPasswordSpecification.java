package com.epam.cafe.repository.specification;

import com.epam.cafe.api.EntitySpecification;
import com.epam.cafe.api.SqlSpecification;
import com.epam.cafe.entitie.user.User;

import java.util.Arrays;
import java.util.List;

public class UserByLoginAndPasswordSpecification implements EntitySpecification<User>, SqlSpecification {
    private static final String QUERY = "SELECT * FROM user " +
            "LEFT JOIN account ON user.AccountID = account.ID " +
            "WHERE Login = ? AND Password = md5(?)";

    private String desiredLogin;
    private String desiredPassword;

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
    public String toSqlClause() {
        return QUERY;
    }

    @Override
    public List<Object> getParams() {
        return Arrays.asList(desiredLogin, desiredPassword);
    }
}
