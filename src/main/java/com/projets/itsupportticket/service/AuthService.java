package com.projets.itsupportticket.service;

import com.projets.itsupportticket.domain.User;

import java.util.Optional;

public interface AuthService {
    void register(User user);
    Optional<User> login(String email, String password);
}
