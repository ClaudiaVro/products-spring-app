package com.clodi.exception;

import java.util.Map;

// TODO move this to invalidProductException
public class ArgumentErrorWrapper {
    private Map<String, String> errors;

    public ArgumentErrorWrapper(Map<String, String> errors) {
        this.errors = errors;
    }

    public ArgumentErrorWrapper() {
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}