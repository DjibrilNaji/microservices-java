package com.spring.authentification.controller;

import com.spring.authentification.dto.LoginRequestDto;
import com.spring.authentification.dto.LoginResponseDto;
import com.spring.authentification.dto.RegisterRequestDto;
import com.spring.authentification.dto.RegisterResponseDto;
import com.spring.authentification.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto response = userService.authenticateUser(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
        final var response = userService.addUser(registerRequestDto.getUsername(), registerRequestDto.getPassword());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
