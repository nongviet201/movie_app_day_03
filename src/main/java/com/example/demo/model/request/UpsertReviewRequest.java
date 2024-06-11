package com.example.demo.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "Nội dung không được để trống")
    String content;
    @NotNull(message = "Không được để trống Rating")
    Integer rating;
    @NotNull(message = "id movie không được để trống")
    Integer movieId;

}
