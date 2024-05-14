package com.example.demo.servies;

import com.example.demo.entity.Episode;

import java.util.List;

public interface EpisodeService {
    List<Episode> findById(Integer movieId);
}
