package com.projets.itsupportticket.mapper.helper;

import com.projets.itsupportticket.domain.User;
import com.projets.itsupportticket.exception.CustomValidationException;
import com.projets.itsupportticket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapperHelper {

    private final UserRepository userRepository;

    public User toUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomValidationException("User with id : " + userId + " not found"));
    }
}
