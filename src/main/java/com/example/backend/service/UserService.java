package com.example.backend.service;

import com.example.backend.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<Users> findAll();
    void saveUser(Users users);
}
