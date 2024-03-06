package com.clodi.exception;

import java.util.logging.Logger;

/**
 * @author Claudia Vidican
 */
public class UndeterminedGenericException extends Exception {

    private static final Logger LOGGER = Logger.getLogger(UndeterminedGenericException.class.getName());
    private final String message;

    public UndeterminedGenericException(String message) {
        this.message = message;
        LOGGER.info("Generic Exception thrown - " + UndeterminedGenericException.class.getName() + message);
    }

    public String getGenericMessage() {
        return message;
    }
}
