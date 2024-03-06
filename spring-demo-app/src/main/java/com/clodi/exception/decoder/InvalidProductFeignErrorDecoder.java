package com.clodi.exception.decoder;

import com.clodi.exception.ArgumentErrorWrapper;
import com.clodi.exception.InvalidProductException;
import com.clodi.exception.UndeterminedGenericException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.InputStream;

/**
 * @author Claudia Vidican
 */
public class InvalidProductFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        if (methodKey.equals("ProductProxy#addProduct(ProductDTO)")) {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream;
            try {
                inputStream = response.body().asInputStream();
                ArgumentErrorWrapper argumentErrorWrapper = mapper.readValue(inputStream, ArgumentErrorWrapper.class);
                return new InvalidProductException(argumentErrorWrapper.getErrors());
            } catch (Exception e) {
                return new UndeterminedGenericException(e.getMessage());
            }
        }
        return new RuntimeException();
    }
}
