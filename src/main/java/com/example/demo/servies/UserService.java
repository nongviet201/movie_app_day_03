package com.example.demo.servies;

import com.example.demo.entity.User;
import com.example.demo.model.request.UpdateUserPasswordRequest;
import com.example.demo.model.request.UpdateUserRequest;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Integer id);

    void updateUser(UpdateUserRequest user);

    void updatePassword(UpdateUserPasswordRequest request);
}
