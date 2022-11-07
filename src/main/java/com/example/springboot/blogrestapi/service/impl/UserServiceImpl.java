package com.example.springboot.blogrestapi.service.impl;

import com.example.springboot.blogrestapi.entity.User;
import com.example.springboot.blogrestapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {
    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
