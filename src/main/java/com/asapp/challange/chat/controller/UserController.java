package com.asapp.challange.chat.controller;

import com.asapp.challange.chat.entity.User;
import com.asapp.challange.chat.model.UserInfo;
import com.asapp.challange.chat.model.UserResponse;
import com.asapp.challange.chat.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserInfo userInfo) {
        User userDetail = new com.asapp.challange.chat.entity.User();
        userDetail.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDetail.setUsername(userInfo.getUsername());
        userDetail.setRoles("user");
        User newUser = userService.save(userDetail);
        return ResponseEntity.ok().body(new UserResponse((newUser.getId())));
    }

    //@GetMapping(value = "/user")
    public List<User> getUsers() {
       return userService.getUsersList();
    }
}
