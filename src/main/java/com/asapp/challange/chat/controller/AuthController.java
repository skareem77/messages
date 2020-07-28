package com.asapp.challange.chat.controller;

import com.asapp.challange.chat.model.LoginResponse;
import com.asapp.challange.chat.model.UserInfo;
import com.asapp.challange.chat.service.impl.UserDetailsImpl;
import com.asapp.challange.chat.service.impl.UserDetailsServiceImpl;
import com.asapp.challange.chat.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity token(@RequestBody UserInfo userInfo) {
        ResponseEntity responseEntity;
        LoginResponse loginResponse = new LoginResponse();
        try {
            authenticate(userInfo.getUsername(), userInfo.getPassword());
            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(userInfo.getUsername());
            String token = jwtUtil.generateToken(userDetails.getUsername());
            loginResponse.setId(userDetails.getId());
            loginResponse.setToken(token);
            responseEntity = ResponseEntity.ok().body(loginResponse);
        } catch (Exception e) {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }


    private void authenticate(String username, String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS");
        }
    }
}
