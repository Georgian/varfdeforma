package com.ggrec.backend.controller;

import com.ggrec.backend.domain.User;
import com.ggrec.backend.exception.ResourceNotFoundException;
import com.ggrec.backend.repository.UserRepository;
import com.ggrec.backend.security.CurrentUser;
import com.ggrec.backend.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}