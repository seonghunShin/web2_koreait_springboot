package com.study.koreait.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {
    private HttpStatus statusCode;
    public UserException(String message, HttpStatus code) {
        super(message);
        this.statusCode = code;
    }
}
