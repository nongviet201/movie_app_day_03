package com.example.demo.servies;

import com.example.demo.entity.Episode;
import com.example.demo.model.request.UpsertEpisodeRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface EpisodeService {
    List<Episode> findById(Integer movieId);

    Episode createEpisode(@Valid UpsertEpisodeRequest request);

    Episode getEpisode(Integer movieId, String tap);

    Object findByMovie_IdOrderByDisplayOrderAsc(Integer id);
}
