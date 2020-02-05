package com.delivery.model.exeption;

public class ValidationException extends RuntimeException {
    public ValidationException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
