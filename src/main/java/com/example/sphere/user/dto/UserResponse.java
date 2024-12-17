package com.example.sphere.user.dto;

import java.time.LocalDateTime;

import com.example.sphere.user.User;

public record UserResponse(
    String name,
    String email,
    String avatar,
    String bio,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Long id

) {
    public static UserResponse fromModel(User user){
        return new UserResponse(
                                user.getName(), 
                                user.getEmail(),
                                user.getAvatar(), 
                                user.getBio(), 
                                user.getCreatedAt(), 
                                user.getUpdatedAt(), 
                                user.getId()
            );
    }
}
