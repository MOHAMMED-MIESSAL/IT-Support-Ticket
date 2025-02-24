package com.projets.itsupportticket.web.controller;


import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.mapper.UserMapper;
import com.projets.itsupportticket.service.UserService;
import com.projets.itsupportticket.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projets.itsupportticket.dto.UserCreateDto;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<Page<User>> findAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(200).body(userService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        return ResponseEntity.status(201).body(userService.create(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @Valid @RequestBody UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        return ResponseEntity.status(200).body(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(userService.findById(id));
    }

    @GetMapping("/user-info")
    public ResponseEntity<Optional<User>> getUserInfo(@RequestHeader("Authorization") String token) {
        String userId = jwtUtil.validateToken(token.replace("Bearer ", ""));
        Optional<User> user = userService.findById(UUID.fromString(userId));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
