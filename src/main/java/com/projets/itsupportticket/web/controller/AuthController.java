package com.projets.itsupportticket.web.controller;

import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.dto.LoginRequest;
import com.projets.itsupportticket.dto.UserCreateDto;
import com.projets.itsupportticket.mapper.UserMapper;
import com.projets.itsupportticket.service.AuthService;
import com.projets.itsupportticket.service.UserService;
import com.projets.itsupportticket.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        Optional<User> optionalUser = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        // Here we generate a token for the user if the credentials are valid
        if (optionalUser.isPresent()) {
            String token = jwtUtil.generateToken(optionalUser.get());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.status(200).body(response);
        }

        // If the credentials are invalid, we return a 401 Unauthorized status
        Map<String, String> response = new HashMap<>();
        response.put("message", "Invalid credentials");
        return ResponseEntity.status(401).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        authService.register(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }





}
