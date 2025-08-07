package com.example.jwt_v1.exception;

import com.example.jwt_v1.domain.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloableException{
    @ExceptionHandler(value = IdInvalidException.class)
    public ResponseEntity<RestResponse<Object>> handleBlogAlreadyExistsException(IdInvalidException idInvalidException) {
        RestResponse<Object> res = new RestResponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(idInvalidException.getMessage());
        res.setMessage(idInvalidException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
}
