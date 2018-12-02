package com.example.demo.service;


import com.example.demo.domain.Movie;
import com.example.demo.domain.Review;
import com.example.demo.dto.MovieDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.mapper.ReviewMapper;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final MovieMapper movieMapper;
    private final UserService userService;
    private final ReviewMapper reviewMapper;

    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movieMapper.toDtos(movies);
    }

    public Optional<MovieDTO> getMovie(Long id) {
        return movieRepository.findOneById(id).map(movieMapper::toDto);
    }

    public MovieDTO saveMovie(MovieDTO movieDTO) {
        Movie movie = movieMapper.toEntity(movieDTO);
        userService.getMaybeUser(movieDTO.getAuthorId()).ifPresent(movie::setAuthor);
        return movieMapper.toDto(movieRepository.save(movie));
    }

    public Optional<Movie> getMovieEntity(Long id) {
        return movieRepository.findOneById(id);
    }

    public List<ReviewDTO> getMovieReviews(Long id) {
        List<Review> reviews = reviewRepository.findAllByMovieId(id);
        return reviewMapper.toDtos(reviews);
    }

    public void likeMovie(Long userId, Long movieId) {
        Movie movie = movieRepository.getOne(movieId);
        movieRepository.findOneById(movieId).ifPresent(movie1 -> {
            movie.getLikedMovies().add(userService.getUserEntity(userId));
            movieRepository.save(movie);
        });
    }

}
