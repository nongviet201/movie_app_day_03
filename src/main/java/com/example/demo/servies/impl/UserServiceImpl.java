package com.example.demo.servies.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.request.UpdateUserPasswordRequest;
import com.example.demo.model.request.UpdateUserRequest;
import com.example.demo.repository.UserReponsitory;
import com.example.demo.servies.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserReponsitory userReponsitory;;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    HttpSession session;

    @Override
    public Optional<User> findUserById(Integer id) {
        return userReponsitory.findById(id);
    }

    @Override
    public void updateUser(UpdateUserRequest userRequest) {
        User user = userReponsitory.findById(userRequest.getId())
                .orElseThrow(() -> new BadRequestException("ID Lỗi"));



        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());


        userReponsitory.save(user);
//      lưu thông tin user vào session dể sử dụng ở các request tiếp theo
        session.setAttribute("currentUser", user);
    }

    @Override
    public void updatePassword(UpdateUserPasswordRequest userRequest) {
        User user = userReponsitory.findById(userRequest.getId())
                .orElseThrow(() -> new BadRequestException("ID Lỗi"));

        // kiểm tra password
        if (!bCryptPasswordEncoder.matches(userRequest.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("mật khẩu cũ không chính xác");
        }

        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getNewPassword()));
        userReponsitory.save(user);
//      lưu thông tin user vào session dể sử dụng ở các request tiếp theo
        session.setAttribute("currentUser", user);
    }

}
