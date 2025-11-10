package com.example.fitTrackBackend.controller;

import com.example.fitTrackBackend.Model.UserAccounts;
import com.example.fitTrackBackend.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users/")
@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserAccounts>authenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccounts currentUser = (UserAccounts) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserAccounts>> allUsers(){
        List<UserAccounts> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
}
