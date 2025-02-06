package com.br.studysecurity.controller;

import com.br.studysecurity.entity.User;
import com.br.studysecurity.entity.UserDTO;
import com.br.studysecurity.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody  User user) {
        return ResponseEntity.ok().body(userService.verify(user));
    }

}
