package com.project.test.chat.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class DuplicatedUserInfoException extends Exception{

    private final HttpStatusCode httpStatus = HttpStatus.CONFLICT;
    public DuplicatedUserInfoException(String resaon){
        super(resaon);
    }
}
