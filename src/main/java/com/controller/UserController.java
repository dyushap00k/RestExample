package com.controller;

import com.model.dto.UserDto;
import com.model.entity.User;
import com.service.DtoMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        Optional<User> optionalUser = userService.getUserById(id);
        return optionalUser
                .map(user -> ResponseEntity.ok().body(DtoMapper.toDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
