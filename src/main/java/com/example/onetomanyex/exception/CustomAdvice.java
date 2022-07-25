package com.example.onetomanyex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomAdvice extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public CustomErrorResponse handleNotFoundException(NotFoundException e){

        return CustomErrorResponse.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
    }

}
