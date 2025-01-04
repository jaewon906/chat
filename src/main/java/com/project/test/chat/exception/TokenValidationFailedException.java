package com.project.test.chat.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class TokenValidationFailedException extends Exception{

    private final HttpStatusCode httpStatus = HttpStatus.UNAUTHORIZED;
    public TokenValidationFailedException(String resaon){
        super(resaon);
    }
}
