package com.example.authenticationBackend.service;

import com.example.authenticationBackend.models.ApplicationUser;
import com.example.authenticationBackend.models.LoginResponseDTO;
import com.example.authenticationBackend.models.Role;
import com.example.authenticationBackend.repository.RoleRepository;
import com.example.authenticationBackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    public ApplicationUser register(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        List<Role> authority = new ArrayList<>();
        authority.add(userRole);
        return userRepository.save(new ApplicationUser(username , encodedPassword , authority , email));

    }
    public LoginResponseDTO loginUser(String email, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepository.findByEmail(email).get(), token);
        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }
}