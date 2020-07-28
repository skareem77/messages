package com.asapp.challange.chat.service.impl;

import com.asapp.challange.chat.entity.User;
import com.asapp.challange.chat.repository.UserRepository;
import com.asapp.challange.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName)
                .orElseThrow( () -> new UsernameNotFoundException("User not found") );
        return new UserDetailsImpl(user);
    }
}
