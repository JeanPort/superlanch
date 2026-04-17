package com.jean.superlanch.common.exception;

public class NameAlreadyExistsException extends BusinessException{

    public NameAlreadyExistsException(String message) {
        super(message);
    }

    public NameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
