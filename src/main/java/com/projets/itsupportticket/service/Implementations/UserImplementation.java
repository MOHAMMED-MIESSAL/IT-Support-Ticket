package com.projets.itsupportticket.service.Implementations;

import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.repository.UserRepository;
import com.projets.itsupportticket.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * UserImplementation
 */

@Service
@RequiredArgsConstructor
public class UserImplementation implements UserService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(UUID id, User user) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("User with id : " + id + " not found");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("User with id : " + id + " not found");
        }
        userRepository.deleteById(id);
    }

}
