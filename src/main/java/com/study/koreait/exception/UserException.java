package com.study.koreait.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends BusinessException {
    public UserException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}