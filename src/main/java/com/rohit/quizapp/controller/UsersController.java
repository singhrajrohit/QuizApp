package com.rohit.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohit.quizapp.model.Users;
import com.rohit.quizapp.service.UsersService;

@RestController
@RequestMapping("user")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody Users user){
        return usersService.registerUser(user);
    }
}
