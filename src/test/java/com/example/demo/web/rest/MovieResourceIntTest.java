package com.example.demo.web.rest;


import com.example.demo.domain.Movie;
import com.example.demo.domain.Rate;
import com.example.demo.domain.UserEntity;
import com.example.demo.mapper.MovieMapper;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;
import com.example.demo.service.RateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieResourceIntTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RateService rateService;

    @Autowired
    private ObjectMapper json;


    @Autowired
    private MockMvc restMovieMockMvc;

    private Movie movie;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }


    private Movie createMovie() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setDescription("test");
        movie.setReleaseDate(LocalDate.ofEpochDay(0L));
        movie.setDirector("test");
        movie.setTitle("test");
        movie.setAuthor(new UserEntity(1L, "test", "test", "test"));
        return movie;
    }


    @Test
    @Transactional
    public void shouldGetAllMovies() throws Exception {
        Movie movie = createMovie();


        movieRepository.saveAndFlush(movie);

        restMovieMockMvc.perform(get("/api/movies/"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$..[0].id").exists())
                .andExpect(jsonPath("$..[0].title").exists())
                .andExpect(jsonPath("$..[0].director").exists())
                .andExpect(jsonPath("$..[0].description").exists())
                .andExpect(jsonPath("$..[0].releaseDate").exists())
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    public void shouldGetMovie() throws Exception {
        Movie movie = createMovie();
        movieRepository.saveAndFlush(movie);

        restMovieMockMvc.perform(get("/api/movies/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.director").exists())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.releaseDate").exists())
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldCreateMovie() throws Exception {
        Movie movie = createMovie();
        restMovieMockMvc.perform(post("/api/movies/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.writeValueAsString(movie)))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    @Transactional
    public void shouldUpdateMovie() throws Exception {
        Movie movie = createMovie();
        movieRepository.saveAndFlush(movie);

        restMovieMockMvc.perform(put("/api/movies")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.writeValueAsString(movie)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    public void shouldLikeMovie() throws Exception {
        Movie movie = createMovie();

        movie.setLikedMovies(new ArrayList<>());
        movie.getLikedMovies().add(new UserEntity(1L, "test", "test", "test"));

        movieRepository.saveAndFlush(movie);

        restMovieMockMvc.perform(get("/api/movies/like/1/1"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$.movieLikes[*]").value(hasItem(1L)))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @Transactional
    public void shouldRateMovie() throws Exception {
        Movie movie = createMovie();

        Rate rate = new Rate(1L, 1, movie, new UserEntity(1L, "test", "test", "test"));

        restMovieMockMvc.perform(post("/api/movies/rate/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.writeValueAsString(rate)))
                .andExpect(status().isCreated())
                .andDo(print());

    }
}
