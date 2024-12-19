package com.example.sphere.user.dto;

import java.time.LocalDateTime;

import com.example.sphere.user.User;

public record UserFormRequest(
    String name,
    String bio,
    String email,
    String password
) {
    public User toModel(){
        return User.builder()
                        .bio(bio)
                        .email(email)
                        .name(name)
                        .password(password)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
    }
}
