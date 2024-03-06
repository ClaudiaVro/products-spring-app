package com.clodi.advice;

import com.clodi.exception.ArgumentErrorWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Claudia Vidican
 */
@RestControllerAdvice
public class AppAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentErrorWrapper> handleProductNotFoundException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getAllErrors().stream().collect(
                Collectors.toMap(e -> ((FieldError) e).getField(), e -> e.getDefaultMessage()));
        return new ResponseEntity<>(new ArgumentErrorWrapper(errors), HttpStatus.BAD_REQUEST);
    }
}
