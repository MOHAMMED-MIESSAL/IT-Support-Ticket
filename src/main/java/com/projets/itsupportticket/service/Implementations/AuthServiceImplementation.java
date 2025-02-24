package com.projets.itsupportticket.service.Implementations;


import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.exception.CustomValidationException;
import com.projets.itsupportticket.service.AuthService;
import com.projets.itsupportticket.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final UserService userService;

    @Override
    public Optional<User> login(String email, String password) {
        return Optional.ofNullable(userService.login(email, password)
                .orElseThrow(() -> new CustomValidationException("Invalid credentials")));
    }

    @Override
    public void register(User user) {
        userService.create(user);
    }
}
