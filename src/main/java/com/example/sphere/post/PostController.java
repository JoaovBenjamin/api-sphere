package com.example.sphere.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sphere.post.dto.PostRequest;
import com.example.sphere.user.UserRepository;

@RestController
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Page<Post> findAll(@PageableDefault(size = 3) Pageable pageable){
            return postService.findAll(pageable);
    }

    @PostMapping
    public Post createPost(@RequestBody PostRequest postRequest){
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        var post = postRequest.toModel(user);
        return postService.createPost(post);
    }
}
