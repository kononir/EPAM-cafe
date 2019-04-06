package com.epam.web.epam.cafe.logic;

import com.epam.web.epam.cafe.entitie.user.User;
import com.epam.web.epam.cafe.entitie.user.UserRole;
import com.epam.web.epam.cafe.logic.exception.UserAuthorizationException;
import com.epam.web.epam.cafe.repository.Repository;
import com.epam.web.epam.cafe.repository.exception.QueryExecutionException;
import com.epam.web.epam.cafe.repository.exception.SqlConvertingException;
import com.epam.web.epam.cafe.repository.factory.RepositoryFactory;
import com.epam.web.epam.cafe.repository.factory.exception.RepositoryFactoryException;
import com.epam.web.epam.cafe.repository.specification.impl.UserByLoginAndPasswordSpecification;

import java.util.List;

public class UserService {
    private static final int FIRST = 0;

    public UserRole authorize(String login, String password) throws UserAuthorizationException {
        UserRole role;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            factory.startTransaction();

            Repository<User> userRepository = factory.userRepository();
            List<User> users = userRepository.query(new UserByLoginAndPasswordSpecification(login, password));

            if (!users.isEmpty()) {
                User user = users.get(FIRST);
                role = user.getRole();
            } else {
                role = UserRole.ANONYMOUS;
            }
        } catch (SqlConvertingException | QueryExecutionException | RepositoryFactoryException e) {
            throw new UserAuthorizationException(e);
        }

        return role;
    }
}
