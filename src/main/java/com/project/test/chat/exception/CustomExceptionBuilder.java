package com.project.test.chat.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class CustomExceptionBuilder {
    private HttpStatusCode status;
    private String message;
    private LocalDateTime timestamp;
}
