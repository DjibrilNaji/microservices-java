package com.spring.authentification.dto;

import org.springframework.http.HttpStatus;

public class RegisterResponseDto {
    private HttpStatus status;
    private String message;

    public RegisterResponseDto(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
