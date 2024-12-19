package com.example.sphere.user.dto;

import java.time.LocalDateTime;

import com.example.sphere.user.User;

public record UserResponse(
    String name,
    String email,
    String bio,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Long id

) {
    public static UserResponse fromModel(User user){
        return new UserResponse(
                                user.getName(), 
                                user.getEmail(),
                                user.getBio(), 
                                user.getCreatedAt(), 
                                user.getUpdatedAt(), 
                                user.getId()
            );
    }
}
