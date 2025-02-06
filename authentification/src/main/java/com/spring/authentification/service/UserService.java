package com.spring.authentification.service;

import com.spring.authentification.dto.LoginResponseDto;
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
        byte[] keyBytes = Decoders.BASE64.decode("f85831164ceaa621a5657a9afb742b4a855e1b1dcf3955c210027eb8ab3867af2c5bc8457309619fea44592d55cb39e705a9b97c4893a96845584b89d266db55f580560d76fc03ca8a773cb6bc66ce000d604bd355f2f8d7a0aafd975717b9b868745c3191bbc800257984b18695d6b7072a79dfa1cddb4fb63b55c90b74c930d1ef5a43e5455dbc95e0f1abce7483f9e7f21164de903b3ccde0e274f5b7adc3bbe80eeb5c37c6ca7fd87fb64df5aa813ddf7932e6a402b60f87ed1396c1ee673a29fd92ca0cf621201a68053bd42db961530e09f038d4eeb044485d36a2f8831ac4fd4f2daa28a3e32b19518f3aacf1c96317ca43942655c4232a6da3081efa");
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 heure
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
}
