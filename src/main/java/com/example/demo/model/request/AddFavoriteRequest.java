package com.example.demo.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddFavoriteRequest {
    @NotNull(message = "userId không để trống")
    int userId;
    @NotNull(message = "movieId không để trống")
    int movieId;
}
