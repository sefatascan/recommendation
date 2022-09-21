package com.sefa.api.excepiton;

import com.sefa.api.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception) {
        return new ResponseEntity<>(ApiErrorResponse.builder().message(exception.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
