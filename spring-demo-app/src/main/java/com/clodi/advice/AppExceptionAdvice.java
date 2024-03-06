package com.clodi.advice;

import com.clodi.exception.ImageNotFoundException;
import com.clodi.exception.InvalidProductException;
import com.clodi.exception.ProductNotFoundException;
import com.clodi.exception.UndeterminedGenericException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice public class AppExceptionAdvice {

    @ExceptionHandler({ ProductNotFoundException.class, ImageNotFoundException.class,
                        UndeterminedGenericException.class }) public ModelAndView handleProductNotFoundException() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("product-not-found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }

    @ExceptionHandler(InvalidProductException.class) public ModelAndView handleProductNotFoundException(InvalidProductException ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add-product-form");
        mav.addObject("errors", ex.getErrors());
        return mav;
    }

}
