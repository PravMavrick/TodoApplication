package com.todoapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler({TodoIsNotFound.class})
    public ResponseEntity<ApiResponse> totalQuantityShouldNotBeLessThanZero(TodoIsNotFound ex){

        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ApiResponse> nullPointerException(NullPointerException ex){

        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.BAD_REQUEST).message(ex.getMessage()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResponse> illegalArgumentException(IllegalArgumentException ex){

        ApiResponse apiResponse = ApiResponse.builder().status(HttpStatus.BAD_REQUEST).message(ex.getMessage()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
