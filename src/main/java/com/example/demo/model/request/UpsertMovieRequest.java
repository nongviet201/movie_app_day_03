package com.example.demo.model.request;

import com.example.demo.entity.Country;
import com.example.demo.model.enums.MovieType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertMovieRequest {
    String movieName;
    String description;
    Integer releaseYear;
    MovieType movieType;
    boolean status;
    String trailer;
    Integer countryId;
    List<Integer> genreIds;
    List<Integer> actorIds;
    List<Integer> directorIds;
}
