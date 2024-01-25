package com.example.authenticationBackend.controllers;

import com.example.authenticationBackend.models.ApplicationUser;
import com.example.authenticationBackend.models.LoginResponseDTO;
import com.example.authenticationBackend.models.RegisterDTO;
import com.example.authenticationBackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegisterDTO body) {
        return authenticationService.register(body.getUsername(),body.getEmail(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegisterDTO body) {
        return authenticationService.loginUser(body.getEmail(), body.getPassword());
    }
}