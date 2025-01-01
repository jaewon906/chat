package com.project.test.chat.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(DuplicatedUserInfoException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(DuplicatedUserInfoException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", ex.getHttpStatus());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
}
