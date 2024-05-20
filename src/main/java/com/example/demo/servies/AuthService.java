package com.example.demo.servies;

import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.RegisterRequest;

public interface AuthService {

    void login(LoginRequest request);

    void register(RegisterRequest request);

    void logout();
}
