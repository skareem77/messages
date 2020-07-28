package com.asapp.challange.chat.service;


import com.asapp.challange.chat.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(int id);
    User save(User user);
    List<User> getUsersList();
}
