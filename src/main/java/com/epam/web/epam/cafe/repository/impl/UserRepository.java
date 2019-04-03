package com.epam.web.epam.cafe.repository.impl;

import com.epam.web.epam.cafe.entitie.Account;
import com.epam.web.epam.cafe.entitie.user.User;
import com.epam.web.epam.cafe.entitie.user.UserRole;
import com.epam.web.epam.cafe.repository.Repository;
import com.epam.web.epam.cafe.repository.specification.Specification;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class UserRepository implements Repository<User> {

    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public List<User> query(Specification<User> specification) {
        return Collections.singletonList(
                new User(
                        0,
                        "",
                        "",
                        "",
                        "",
                        0,
                        UserRole.CLIENT,
                        new Account(0, new BigDecimal(0))
                )
        );
    }
}
