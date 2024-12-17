package com.example.sphere.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sphere.user.UserRepository;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public Token login(Credentials credentials){
        var user = userRepository.findByEmail(credentials.email())
        .orElseThrow(() -> new RuntimeException("Acesso negado"));

        if(!passwordEncoder.matches(credentials.password(), user.getPassword())) 
        throw new RuntimeException("Acesso negado");

        return tokenService.createToken(credentials.email());
    }
}
