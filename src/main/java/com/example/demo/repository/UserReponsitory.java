package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReponsitory extends JpaRepository<User, Integer> {
    // tìm theo role
    List<User> findByRole(UserRole role);

}
