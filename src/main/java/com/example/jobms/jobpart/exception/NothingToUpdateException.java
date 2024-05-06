package com.example.jobms.jobpart.exception;

public class NothingToUpdateException extends RuntimeException {
    public NothingToUpdateException() {
        super();
    }

    public NothingToUpdateException(String message) {
        super(message);
    }
}
