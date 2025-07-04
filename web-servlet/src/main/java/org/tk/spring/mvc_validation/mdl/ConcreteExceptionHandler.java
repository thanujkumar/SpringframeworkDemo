package org.tk.spring.mvc_validation.mdl;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//Specific exception handled by this class
@ControllerAdvice
public class ConcreteExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public String handleIllegalStateException(Exception e) {
        return "Error occurred " + e.getMessage();
    }
}
