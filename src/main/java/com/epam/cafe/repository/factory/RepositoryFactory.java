package com.epam.cafe.repository.factory;

import com.epam.cafe.api.Repository;
import com.epam.cafe.connection.ConnectionPool;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.repository.exception.RepositoryException;
import com.epam.cafe.repository.impl.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class RepositoryFactory implements AutoCloseable {
    private Connection connection;

    private static final int TIMEOUT = 1;

    public RepositoryFactory() throws RepositoryException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            connection = pool.retrieveConnection();
        } catch (InterruptedException e) {
            throw new RepositoryException("Getting connection exception.", e);
        }
    }

    public Repository<User> userRepository() {
        return new UserRepository(connection);
    }

    public void startTransaction() throws RepositoryException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RepositoryException("Starting transaction error.", e);
        }
    }

    public void finishTransaction() throws RepositoryException {
        try {
            if (connection.isValid(TIMEOUT)) {
                connection.commit();
            } else {
                connection.rollback();
            }

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RepositoryException("Finishing transaction error.", e);
        }
    }

    @Override
    public void close() throws RepositoryException {
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            pool.storeConnection(connection);
        } catch (InterruptedException e) {
            throw new RepositoryException("Closing repository error.", e);
        }
    }
}
