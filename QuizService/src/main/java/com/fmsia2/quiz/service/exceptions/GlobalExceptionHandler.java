package com.fmsia2.quiz.service.exceptions;

import com.fmsia2.quiz.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse res = new ApiResponse(message,true, HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
    }
}
