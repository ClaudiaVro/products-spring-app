package com.clodi.exception;

/**
 * @author Claudia Vidican
 */
public class EmailErrorException extends RuntimeException {

    public EmailErrorException(String message) {
        super(message);
    }
}
