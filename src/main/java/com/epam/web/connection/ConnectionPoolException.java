package com.epam.web.connection;

public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
