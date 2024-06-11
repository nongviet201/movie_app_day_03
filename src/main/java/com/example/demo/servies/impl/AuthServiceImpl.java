package com.example.demo.servies.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.enums.UserRole;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.RegisterRequest;
import com.example.demo.repository.UserReponsitory;
import com.example.demo.servies.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public void login(LoginRequest request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(token);
            session.setAttribute("MY_SESSION", authentication.getName());
        } catch (DisabledException e){
            throw  new BadRequestException("Tài khoản chưa được kích hoạt");
        } catch (AuthenticationException e){
            throw new BadRequestException("Tài khoản hoặc mật khẩu chưa chính xác");
        }

//        User users = userReponsitory.findByEmail(request.getEmail())
//                .orElseThrow(() -> new BadRequestException("Email incorrect"));
//
//        // kiểm tra password
//        if (!bCryptPasswordEncoder.matches(request.getPassword(), users.getPassword())) {
//            throw new BadRequestException("password incorrect");
//        }
//        // lưu thông tin user vào session dể sử dụng ở các request tiếp theo
//        session.setAttribute("currentUser", users);
    }

    @Override
    public void register(RegisterRequest request) {
        if (userReponsitory.findByUsername(request.getName()).isPresent()) {
            throw new BadRequestException("Username already in use");
        }

        if (userReponsitory.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already in use");
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

    @Override
    public void logout() {
        session.setAttribute("currentUser", null);
    }


}

