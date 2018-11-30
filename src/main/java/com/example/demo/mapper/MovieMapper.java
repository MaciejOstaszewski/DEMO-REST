package com.example.demo.mapper;

import com.example.demo.domain.Movie;
import com.example.demo.dto.MovieDTO;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements Mapper<Movie, MovieDTO> {

    @Override
    public Movie toEntity(MovieDTO movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setDirector(movieDto.getDirector());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setAuthor(movieDto.getAuthor());
        movie.setLikedMovies(movieDto.getMovieLikes());
        return movie;
    }

    @Override
    public MovieDTO toDto(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setDirector(movie.getDirector());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setAuthor(movie.getAuthor());
        dto.setMovieLikes(movie.getLikedMovies());
        return dto;
    }
}
