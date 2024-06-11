package com.example.demo.model.request;

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
public class UpsertBlogRequest {
    @NotNull(message = "Không được để trống title")
    String title;
    @NotNull(message = "Không được để trống content")
    String content;
    @NotNull(message = "Không được để trống description")
    String description;
    @NotNull(message = "Không được để trống status")
    boolean status;

}
