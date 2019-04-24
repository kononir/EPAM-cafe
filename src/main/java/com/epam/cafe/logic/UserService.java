package com.epam.cafe.logic;

import com.epam.cafe.api.Repository;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.entitie.user.UserRole;
import com.epam.cafe.logic.exception.ServiceException;
import com.epam.cafe.repository.exception.RepositoryException;
import com.epam.cafe.repository.factory.RepositoryFactory;
import com.epam.cafe.repository.specification.UsersByRoleSpecification;
import com.epam.cafe.repository.specification.UserByLoginAndPasswordSpecification;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static final int FIRST = 0;

    public Optional<User> authorize(String login, String password) throws ServiceException {
        Optional<User> user;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<User> userRepository = factory.userRepository();
            List<User> users = userRepository.query(new UserByLoginAndPasswordSpecification(login, password));

            if (!users.isEmpty()) {
                user = Optional.of(users.get(FIRST));
            } else {
                user = Optional.empty();
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Authorization error.", e);
        }

        return user;
    }

    public List<User> getClients() throws ServiceException {
        List<User> clients;

        try (RepositoryFactory factory = new RepositoryFactory()) {
            Repository<User> userRepository = factory.userRepository();
            clients = userRepository.query(new UsersByRoleSpecification(UserRole.CLIENT));
        } catch (RepositoryException e) {
            throw new ServiceException("Getting clients error.", e);
        }

        return clients;
    }
}
