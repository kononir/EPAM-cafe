package com.epam.cafe.logic;

import com.epam.cafe.api.Repository;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.logic.exception.UserAuthorizationException;
import com.epam.cafe.repository.exception.RepositoryException;
import com.epam.cafe.repository.factory.RepositoryFactory;
import com.epam.cafe.repository.factory.exception.RepositoryFactoryException;
import com.epam.cafe.repository.specification.UserByLoginAndPasswordSpecification;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final int FIRST = 0;

    public Optional<User> authorize(String login, String password) throws UserAuthorizationException {
        Optional<User> user;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            factory.startTransaction();

            Repository<User> userRepository = factory.userRepository();
            List<User> users = userRepository.query(new UserByLoginAndPasswordSpecification(login, password));

            if (!users.isEmpty()) {
                user = Optional.of(users.get(FIRST));
            } else {
                user = Optional.empty();
            }
        } catch (RepositoryException | RepositoryFactoryException e) {
            throw new UserAuthorizationException(e);
        }

        return user;
    }
}
