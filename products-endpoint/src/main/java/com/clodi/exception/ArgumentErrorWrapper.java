package com.clodi.exception;

import java.util.Map;

public class ArgumentErrorWrapper {
    private Map<String, String> errors;

    public ArgumentErrorWrapper() {
    }

    public ArgumentErrorWrapper(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}