package com.example.demo.rest;

import com.example.demo.entity.Reviews;
import com.example.demo.model.request.UpsertReviewRequest;
import com.example.demo.servies.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews") //Những api tra ve JSON thì đặt '/api' ở trước
public class MovieApi {
    @Autowired
    private ReviewService reviewService;

    //Tạo reviews
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody UpsertReviewRequest request){
        Reviews reviews = reviewService.createReview(request);
        return new ResponseEntity<>(reviews, HttpStatus.CREATED); //201
    }

    //Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody UpsertReviewRequest request, @PathVariable Integer id){
        Reviews reviews = reviewService.updateReview(id, request);

        return ResponseEntity.ok(reviews); //200
    }
    //Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build(); //204
    }
    //Get
    @GetMapping("/{id}")
    public ResponseEntity<?> getReview(@PathVariable Integer id){
        Reviews review = reviewService.getReview(id);
        return ResponseEntity.ok(review); //200
    }

}
