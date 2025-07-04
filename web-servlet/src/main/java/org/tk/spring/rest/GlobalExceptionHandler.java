package org.tk.spring.rest;

import lombok.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //for ExceptionHandling
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<StockError> handleException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new StockError("00122", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<StockError> handleException(MethodArgumentNotValidException e) {
        MethodParameter parameter = e.getParameter();
        System.out.println("Parameter Name :" + parameter.getParameter().getName());
        System.out.println("Method Name    :" + parameter.getMethod().getName());
        e.getBindingResult().getAllErrors().stream().forEach(System.out::println);
        return ResponseEntity.badRequest().body(new StockError("00123", e.getMessage()));
    }
}

@Value
class StockError {
    private String errorCode;
    private String message;
}
