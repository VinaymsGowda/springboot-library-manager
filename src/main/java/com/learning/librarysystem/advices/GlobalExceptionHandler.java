package com.learning.librarysystem.advices;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        ApiError apiError=ApiError.builder().message(e.getMessage())
                .subErrors(new ArrayList<>())
                .build();
        return new ResponseEntity<>(new ApiResponse<>(apiError), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<?>> handleException(ExpiredJwtException e) {
        ApiError apiError=ApiError.builder().message(e.getMessage())
                .subErrors(new ArrayList<>())
                .build();
        return new ResponseEntity<>(new ApiResponse<>(apiError), HttpStatus.CONFLICT);
    }
}
