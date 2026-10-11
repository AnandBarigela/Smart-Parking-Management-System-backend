package com.smartparking.controller;

import com.smartparking.entity.User;
import com.smartparking.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository users;

    public UserController(UserRepository users) {
        this.users = users;
    }

    @GetMapping
    public List<User> all() {
        return users.findAll();
    }

    @GetMapping("/{id}")
    public User one(@PathVariable Long id) {
        return users.findById(id).orElseThrow();
    }
}
