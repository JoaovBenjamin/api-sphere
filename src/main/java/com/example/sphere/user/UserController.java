package com.example.sphere.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.sphere.user.dto.UserFormRequest;
import com.example.sphere.user.dto.UserResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping()
    public List<UserResponse> findAll(@RequestParam String name) {
        return userService
                         .findByName(name)
                         .stream()
                         .map(UserResponse::fromModel)
                         .toList();
    }

    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody UserFormRequest userForm, UriComponentsBuilder uBuilder) {
        var user = userService.createUser(userForm.toModel());

        var uri = uBuilder
                        .path("/user/{id}")
                        .buildAndExpand(user.getId())
                        .toUri();
                
        return ResponseEntity
                        .created(uri)
                        .body(UserResponse.fromModel(user));
    }
    
    
}
