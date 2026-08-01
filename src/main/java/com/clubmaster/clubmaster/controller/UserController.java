package com.clubmaster.clubmaster.controller;

import com.clubmaster.clubmaster.dto.UserDTO;
import com.clubmaster.clubmaster.entity.User;
import com.clubmaster.clubmaster.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public UserDTO findByLogin(@PathVariable String username) {
        return userService.findByLogin(username);
    }
}
