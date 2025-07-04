package com.my.bookmaster.exception;

public class AlreadyExitsException extends RuntimeException {
    public AlreadyExitsException(String message) {
        super(message);
    }
}
