package com.epam.web.epam.cafe.repository.specification.impl;

import com.epam.web.epam.cafe.entitie.user.User;
import com.epam.web.epam.cafe.repository.specification.Specification;

public class UserByLoginAndPasswordSpecification implements Specification<User> {
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
}
