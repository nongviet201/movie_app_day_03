package com.example.demo.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertEpisodeRequest {
    Integer displayOder;
    String name;
    Double duration;
    Integer movieId;
}
