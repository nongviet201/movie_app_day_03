package com.example.demo.servies.impl;

import com.example.demo.entity.Episode;
import com.example.demo.repository.EpisodeReponsitory;
import com.example.demo.servies.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpidoseServiceImpl implements EpisodeService {
    @Autowired
    EpisodeReponsitory episodeReponsitory;

    @Override
    public List<Episode> findById(Integer movieId) {
        return episodeReponsitory.findByMovieIdOrderByDisplayOrder(movieId);
    }
}
