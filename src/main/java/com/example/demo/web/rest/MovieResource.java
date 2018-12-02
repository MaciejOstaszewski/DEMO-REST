package com.example.demo.web.rest;


import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.RateDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.service.MovieService;
import com.example.demo.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/movies")
public class MovieResource {

    private final MovieService movieService;
    private final RateService rateService;


    @GetMapping
    public List<MovieDTO> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public ResponseEntity<MovieDTO> saveMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.saveMovie(movieDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MovieDTO> updateMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.saveMovie(movieDTO), HttpStatus.OK);
    }

    @GetMapping("reviews/{id}")
    public List<ReviewDTO> getMovieReviews(@PathVariable Long id) {
        return movieService.getMovieReviews(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        return movieService.getMovie(id).map(movieDTO -> new ResponseEntity<>(movieDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/rate")
    public ResponseEntity<RateDTO> rateMovie(@RequestBody RateDTO rateDTO) {
        return new ResponseEntity<>(rateService.addRate(rateDTO), HttpStatus.CREATED);

    }

    @GetMapping("like/{idu}/{idm}")
    public ResponseEntity<Void> likeMovie(@PathVariable Long idu, @PathVariable Long idm) {
        movieService.likeMovie(idu, idm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
