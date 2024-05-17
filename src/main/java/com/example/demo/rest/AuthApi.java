package com.example.demo.rest;

import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.RegisterRequest;
import com.example.demo.servies.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authService.login(request);
        return ResponseEntity.ok("login successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("register successfully");
    }
}
