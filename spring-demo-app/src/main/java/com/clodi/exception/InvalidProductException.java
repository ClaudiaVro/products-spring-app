package com.clodi.exception;

import java.util.Map;

/**
 * @author Claudia Vidican
 */
public class InvalidProductException extends Exception {
    private final Map<String, String> errors;

    public InvalidProductException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
