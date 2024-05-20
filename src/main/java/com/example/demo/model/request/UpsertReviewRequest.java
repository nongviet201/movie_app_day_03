package com.example.demo.model.request;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertReviewRequest {
    @NotBlank(message = "Email không để trống")
    @Email(message = "Email không đúng định dạng")
    String content;
    @NotBlank(message = "Email không để trống")
    @Email(message = "Email không đúng định dạng")
    Integer rating;
    @NotBlank(message = "Email không để trống")
    Integer movieId;

}
