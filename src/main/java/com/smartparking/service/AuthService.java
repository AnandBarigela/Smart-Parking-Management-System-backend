package com.smartparking.service;

import com.smartparking.dto.LoginRequest;
import com.smartparking.dto.RegisterRequest;
import com.smartparking.entity.Role;
import com.smartparking.entity.User;
import com.smartparking.repository.UserRepository;
import com.smartparking.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(UserRepository users, PasswordEncoder encoder, JwtService jwt) {
        this.users = users;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public Map<String, Object> register(RegisterRequest request) {
        if (users.findByEmail(request.email()).isPresent())
            throw new IllegalArgumentException("Email already registered");

        User user = new User(
                request.name(),
                request.email(),
                encoder.encode(request.password()),
                Role.USER
        );
        users.save(user);

        String token = jwt.generate(user.getEmail(), user.getRole().name(), user.getId());
        return response(user, token);
    }

    public Map<String, Object> login(LoginRequest request) {
        User user = users.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!encoder.matches(request.password(), user.getPassword()))
            throw new IllegalArgumentException("Invalid email or password");

        String token = jwt.generate(user.getEmail(), user.getRole().name(), user.getId());
        return response(user, token);
    }

    private Map<String,Object> response(User user, String token) {
        return Map.of(
                "token", token,
                "id", user.getId(),
                "name", user.getName(),
                "email", user.getEmail(),
                "role", user.getRole().name()
        );
    }
}
