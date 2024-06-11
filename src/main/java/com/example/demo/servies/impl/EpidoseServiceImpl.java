package com.example.demo.servies.impl;

import com.example.demo.entity.Episode;
import com.example.demo.entity.Movie;
import com.example.demo.model.request.UpsertEpisodeRequest;
import com.example.demo.repository.EpisodeReponsitory;
import com.example.demo.repository.MovieRepository;
import com.example.demo.servies.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EpidoseServiceImpl implements EpisodeService {
    @Autowired
    EpisodeReponsitory episodeReponsitory;
    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Episode> findById(Integer movieId) {
        return episodeReponsitory.findByMovieIdOrderByDisplayOrder(movieId);
    }

    @Override
    public Episode createEpisode(UpsertEpisodeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + request.getMovieId()));

        Episode episode = Episode.builder()
                .name(request.getName())
                .duration(request.getDuration())
                .videoUrl(null)
                .displayOrder(request.getDisplayOder())
                .createAt(LocalDate.now())
                .updateAt(LocalDate.now())
                .movie(movie)
                .build();
        episodeReponsitory.save(episode);
        return episode;
    }

    @Override
    public Episode getEpisode(Integer movieId, String tap) {
        if (tap.equals("full")) {
            return episodeReponsitory
                    .findByMovie_IdAndMovie_StatusAndDisplayOrder(movieId, true, 1)
                    .orElse(null);
        } else {
            return episodeReponsitory
                    .findByMovie_IdAndMovie_StatusAndDisplayOrder(movieId, true, Integer.parseInt(tap))
                    .orElse(null);
        }
    }

    @Override
    public Object findByMovie_IdOrderByDisplayOrderAsc(Integer id) {
        return episodeReponsitory.findByMovie_IdAndMovie_StatusOrderByDisplayOrderAsc(id, true);
    }
}
