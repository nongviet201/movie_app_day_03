package com.example.demo.rest;

import com.example.demo.entity.Episode;
import com.example.demo.entity.Movie;
import com.example.demo.model.request.UpsertEpisodeRequest;
import com.example.demo.model.request.UpsertMovieRequest;
import com.example.demo.servies.EpisodeService;
import com.example.demo.servies.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/movies")
public class MovieApi {
    @Autowired
    private MovieService movieService;
    @Autowired
    private EpisodeService episodeService;

    //Tạo Movies
    @PostMapping
    public ResponseEntity<?> createMovie(@Valid @RequestBody UpsertMovieRequest request){
        Movie movie = movieService.createMovie(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED); //201
    }

    //Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@Valid @RequestBody UpsertMovieRequest request, @PathVariable Integer id){
        Movie movie = movieService.updateMovie(id, request);

        return ResponseEntity.ok(movie); //200
    }
    //Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@Valid @PathVariable Integer id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build(); // Trả về mã trạng thái 204 khi xóa thành công
    }
    //Get
    @GetMapping("/{id}")
    public ResponseEntity<?> getMovie(@Valid @PathVariable Integer id){
        Movie movie = movieService.getMovie(id);
        return ResponseEntity.ok(movie); //200
    }

    @PostMapping("/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@PathVariable Integer id,
                                             @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(movieService.uploadThumbnail(id, file));
    }


    @PostMapping("/create-episode")
    public ResponseEntity<?> createEpisode (@Valid @RequestBody UpsertEpisodeRequest request) {
        Episode episode = episodeService.createEpisode(request);
        return new ResponseEntity<>(episode, HttpStatus.CREATED);
    }
}
