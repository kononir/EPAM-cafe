package com.epam.cafe.repository.factory;

import com.epam.cafe.api.Repository;
import com.epam.cafe.entitie.user.User;
import com.epam.cafe.repository.factory.exception.RepositoryFactoryException;
import com.epam.cafe.repository.impl.UserRepository;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryFactory implements AutoCloseable {
    private Connection connection;

    private static final int TIMEOUT = 1;

    private static final String URL = "jdbc:mysql://localhost:3306/epam_cafe" +
            "?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Repository<User> userRepository() {
        return new UserRepository(connection);
    }

    public void startTransaction() throws RepositoryFactoryException {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RepositoryFactoryException("Starting transaction error.", e);
        }
    }

    public void finishTransaction() throws RepositoryFactoryException {
        try {
            // code of returning connection to pool

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RepositoryFactoryException("Starting transaction error.", e);
        }
    }

    @Override
    public void close() throws RepositoryFactoryException {
        try {
            if (connection.isValid(TIMEOUT)) {
                connection.commit();
            } else {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException e) {
            throw new RepositoryFactoryException("Closing repository error.", e);
        }
    }
}
