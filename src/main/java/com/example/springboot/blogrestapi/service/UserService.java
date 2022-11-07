package com.example.springboot.blogrestapi.service;

import com.example.springboot.blogrestapi.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);
}
