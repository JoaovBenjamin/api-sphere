package com.example.sphere.auth;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.sphere.user.User;
import com.example.sphere.user.UserRepository;


@Service
public class TokenService {
    
    @Autowired
    UserRepository userRepository;

    Algorithm algorithm;

    public TokenService(UserRepository userRepository,@Value("{jwt.secret}") String secret){
        algorithm = Algorithm.HMAC256(secret);
        this.userRepository = userRepository;
    }

    public Token createToken(String email){
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));

        String token = JWT.create()
                            .withSubject(email)
                            .withIssuer("sphere")
                            .withExpiresAt(expiresAt)
                            .sign(algorithm);

        return new Token(token, "JWT");
    }

    public User getUserFromToken(String token){
        var email = JWT.require(algorithm)
                                .build()
                                .verify(token)
                                .getSubject();

        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
