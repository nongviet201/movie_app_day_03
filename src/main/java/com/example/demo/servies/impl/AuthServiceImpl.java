package com.example.demo.servies.impl;

import com.example.demo.entity.User;
import com.example.demo.model.enums.UserRole;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.RegisterRequest;
import com.example.demo.repository.UserReponsitory;
import com.example.demo.servies.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.util.regex.Pattern.matches;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserReponsitory userReponsitory;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    HttpSession session;

    @Override
    public void login(LoginRequest request) {
        User users = userReponsitory.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email incorrect"));

        // kiểm tra password
        if (!bCryptPasswordEncoder.matches(request.getPassword(), users.getPassword())) {
            throw new RuntimeException("password incorrect");
        }

        // lưu thông tin user vào session dể sử dụng ở các request tiếp theo
        session.setAttribute("currentUser", users);
    }

    @Override
    public void register(RegisterRequest request) {
        if (userReponsitory.findByUsername(request.getName()).isPresent()) {
            throw new RuntimeException("Username already in use");
        }

        if (userReponsitory.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User savedUser = User.builder()
                        .username(request.getName())
                        .password(bCryptPasswordEncoder.encode(request.getPassword()))
                        .email(request.getEmail())
                        .avatar("https://placehold.co/60x60?text=A")
                        .role(UserRole.USER)
                        .createAt(LocalDate.now())
                        .updateAt(LocalDate.now())
                        .build();

        userReponsitory.save(savedUser);
        session.setAttribute("currentUser", savedUser);
    }
}

