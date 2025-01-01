package com.project.test.chat.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(DuplicatedUserInfoException.class)
    public ResponseEntity<CustomExceptionBuilder> userAlreadyExistException(DuplicatedUserInfoException ex) {
        return new ResponseEntity<>(makeExceptionBuilder(ex, ex.getHttpStatus()), ex.getHttpStatus());
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<CustomExceptionBuilder> loginFailedException(LoginFailedException ex) {
        return new ResponseEntity<>(makeExceptionBuilder(ex, ex.getHttpStatus()), ex.getHttpStatus());
    }


    private <T extends Exception> CustomExceptionBuilder makeExceptionBuilder(T ex, HttpStatusCode code){
                return CustomExceptionBuilder.builder()
                                      .message(ex.getMessage())
                                      .status(code)
                                      .timestamp(LocalDateTime.now())
                                      .build();
    }
}
