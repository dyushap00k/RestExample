package com.service;

import com.model.dto.UserDto;
import com.model.entity.User;
import com.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
            return DtoMapper.toDto(userOptional.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
    }
}
