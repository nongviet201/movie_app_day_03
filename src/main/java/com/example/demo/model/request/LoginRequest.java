package com.example.demo.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    String email;
    String password;
}
