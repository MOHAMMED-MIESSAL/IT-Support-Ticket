package com.projets.itsupportticket.service.Implementations;



import com.projets.itsupportticket.domain.User;
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
      Optional<User> user = userService.getUserByEmail(email);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return user;
    }

    @Override
    public void register(User user) {
        userService.create(user);
    }
}
