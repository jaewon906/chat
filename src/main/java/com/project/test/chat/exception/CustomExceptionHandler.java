package com.project.test.chat.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

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

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<CustomExceptionBuilder> malformedJwtException(MalformedJwtException ex) {
        return new ResponseEntity<>(makeExceptionBuilder(ex, HttpStatus.UNAUTHORIZED),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<CustomExceptionBuilder> securityException(SecurityException ex) {
        return new ResponseEntity<>(makeExceptionBuilder(ex, HttpStatus.UNAUTHORIZED),HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<CustomExceptionBuilder> expiredJwtException(ExpiredJwtException ex) {
        return new ResponseEntity<>(makeExceptionBuilder(ex, HttpStatus.UNAUTHORIZED),HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<CustomExceptionBuilder> unsupportedJwtException(UnsupportedJwtException ex) {
        return new ResponseEntity<>(makeExceptionBuilder(ex, HttpStatus.UNAUTHORIZED),HttpStatus.UNAUTHORIZED);
    }

    private <T extends Exception> CustomExceptionBuilder makeExceptionBuilder(T ex, HttpStatusCode code){
                return CustomExceptionBuilder.builder()
                                      .message(ex.getMessage())
                                      .status(code)
                                      .timestamp(LocalDateTime.now())
                                      .build();
    }
}
