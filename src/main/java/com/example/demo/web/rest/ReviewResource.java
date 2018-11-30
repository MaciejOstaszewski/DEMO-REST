package com.example.demo.web.rest;


import com.example.demo.dto.ReviewDTO;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/reviews")
public class ReviewResource {

    private final ReviewService reviewService;


    @GetMapping
    public List<ReviewDTO> getReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        return reviewService.getReview(id).map(reviewDTO -> new ResponseEntity<>(reviewDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> saveReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        return new ResponseEntity<>(reviewService.saveReview(reviewDTO), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);

    }

    @GetMapping("like/{idu}/{idr}")
    public ResponseEntity<ReviewDTO> likeReview(@PathVariable Long idu, @PathVariable Long idr) {
        return new ResponseEntity<>(reviewService.likeReview(idu, idr), HttpStatus.ACCEPTED);
    }


}
