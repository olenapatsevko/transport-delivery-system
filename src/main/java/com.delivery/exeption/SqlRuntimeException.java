package com.delivery.exeption;

public class SqlRuntimeException extends RuntimeException {
    public SqlRuntimeException() {
    }

    public SqlRuntimeException(String message) {
        super(message);
    }

    public SqlRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
