package com.example.sphere.user.dto;

import com.example.sphere.user.User;

public record UserProfileResponse(
    String name,
    String  bio,
    String avatar,
    String email
) {
    public UserProfileResponse(User user){
        this(user.getName(), user.getBio(), user.getAvatar(), user.getEmail());
    }
}
