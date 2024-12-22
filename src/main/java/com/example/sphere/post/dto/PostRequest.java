package com.example.sphere.post.dto;

import java.time.LocalDateTime;

import com.example.sphere.post.Post;
import com.example.sphere.user.User;

public record PostRequest(
    String text
) {
    public Post toModel(User user){
        return Post.builder()
            .text(text)
            .createdAt(LocalDateTime.now())
            .user(user)
            .build();
    }
}
