package com.spring.authentification.dto;

import org.springframework.http.HttpStatus;

public class LoginResponseDto {
    private HttpStatus status;
    private String message;
    private String token;

    public LoginResponseDto(HttpStatus httpStatus, String message, String token) {
        this.status = httpStatus;
        this.message = message;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
