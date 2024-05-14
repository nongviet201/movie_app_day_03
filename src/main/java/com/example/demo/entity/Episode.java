package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    String name;
    double duration;
    String videoUrl;
    Integer displayOrder;
    LocalDate createAt;
    LocalDate updateAt;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;
}
