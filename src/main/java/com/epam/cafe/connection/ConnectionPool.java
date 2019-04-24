package com.epam.cafe.connection;

import com.epam.cafe.connection.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;

    private static final int CONNECTIONS_NUMBER = 10;
    private BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(CONNECTIONS_NUMBER);

    private static final Logger LOGGER = LogManager.getRootLogger();

    public ConnectionPool() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            for (int i = 0; i < CONNECTIONS_NUMBER; i++) {
                connections.put(factory.create());
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.fatal("Creating connections exception", e);
            throw new ConnectionPoolException("Creating connections exception", e);
        }
    }

    public static ConnectionPool getInstance() {
        Lock lock = new ReentrantLock();

        try {
            lock.lock();

            if (instance == null) {
                instance = new ConnectionPool();
            }
        } finally {
            lock.unlock();
        }

        return instance;
    }

    public Connection retrieveConnection() throws InterruptedException {
        return connections.take();
    }

    public void storeConnection(Connection connection) throws InterruptedException {
        connections.put(connection);
    }

    public void closeAll() throws SQLException {
        for (Connection connection : connections) {
            connection.close();
        }
    }
}
