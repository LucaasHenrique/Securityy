package com.br.studysecurity.controller;

import com.br.studysecurity.entity.User;
import com.br.studysecurity.entity.UserDTO;
import com.br.studysecurity.service.UserService;
import jdk.incubator.vector.VectorOperators;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(UserDTO userDTO) {
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return ResponseEntity.ok().body(userService.save(user));
    }

    @PostMapping("login")
    public ResponseEntity<Void> login() {
        return ResponseEntity.ok().build();
    }

}
