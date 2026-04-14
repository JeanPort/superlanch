package com.jean.superlanch.common.exception;

public class BusinesException extends RuntimeException{

    public BusinesException(String message) {
        super(message);
    }

    public BusinesException(String message, Throwable cause) {
        super(message, cause);
    }
}
