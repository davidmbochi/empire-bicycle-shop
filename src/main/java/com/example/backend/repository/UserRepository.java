package com.example.backend.repository;

import com.example.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findUserByUserName(String username);
}
