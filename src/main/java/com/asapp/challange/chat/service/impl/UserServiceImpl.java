package com.asapp.challange.chat.service.impl;

import com.asapp.challange.chat.entity.User;
import com.asapp.challange.chat.repository.UserRepository;
import com.asapp.challange.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
       return userRepository.save(user);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }
}
