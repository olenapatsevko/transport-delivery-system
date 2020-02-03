package com.delivery.exeption;

public class ValidationException extends RuntimeException {
    public ValidationException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
