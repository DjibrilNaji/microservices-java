package com.spring.authentification.service;

import com.spring.authentification.dto.LoginResponseDto;
import com.spring.authentification.dto.RegisterResponseDto;
import com.spring.authentification.entity.User;
import com.spring.authentification.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class UserService {

    private static final String SECRET_KEY = "v8HtnMZz1LC9GmXjFqHIk5oZmvqT8zjQK0A0Yuc7HMI=";
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponseDto register(String username, String password) {
        if (!checkUser(username, password)) {
            return new LoginResponseDto(HttpStatus.UNAUTHORIZED, "Invalid credentials", null);
        }

        String token = generateToken(username);
        return new LoginResponseDto(HttpStatus.OK, "Authentication successful", token);
    }

    public LoginResponseDto authenticateUser(String username, String password) {
        if (!checkUser(username, password)) {
            return new LoginResponseDto(HttpStatus.UNAUTHORIZED, "Invalid credentials", null);
        }

        String token = generateToken(username);
        return new LoginResponseDto(HttpStatus.OK, "Authentication successful", token);
    }

    public String generateToken(String username) {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    public boolean checkUser(String username, String password) {
        final var user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }

    public RegisterResponseDto addUser(String username, String password) {
        final var userExisting = userRepository.findByUsername(username);

        if (userExisting != null) {
            return new RegisterResponseDto(HttpStatus.UNAUTHORIZED, "User already exists");
        }

        final var user = new User(username, password);
        userRepository.save(user);

        return new RegisterResponseDto(HttpStatus.OK, "User created");
    }
}
