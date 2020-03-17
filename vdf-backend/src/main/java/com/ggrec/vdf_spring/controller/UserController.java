package com.ggrec.vdf_spring.controller;

import com.ggrec.vdf_spring.domain.User;
import com.ggrec.vdf_spring.exception.ResourceNotFoundException;
import com.ggrec.vdf_spring.repository.UserRepository;
import com.ggrec.vdf_spring.security.CurrentUser;
import com.ggrec.vdf_spring.security.UserPrincipal;
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