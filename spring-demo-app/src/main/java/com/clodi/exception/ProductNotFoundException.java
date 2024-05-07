package com.clodi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial") @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found") public class ProductNotFoundException
                extends RuntimeException {

}
