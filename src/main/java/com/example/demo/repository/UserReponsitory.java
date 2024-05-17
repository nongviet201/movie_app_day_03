package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserReponsitory extends JpaRepository<User, Integer> {
    // tìm theo role
    List<User> findByRole(UserRole role);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String name);
}
