package com.epam.cafe.connection.pool;

public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
