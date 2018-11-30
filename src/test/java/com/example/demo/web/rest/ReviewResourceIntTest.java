package com.example.demo.web.rest;


import com.example.demo.domain.Movie;
import com.example.demo.domain.Review;
import com.example.demo.domain.UserEntity;
import com.example.demo.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReviewResourceIntTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ObjectMapper json;

    @Autowired
    private MockMvc restReviewMockMvc;

    private UserEntity userEntity;
    private Movie movie;

    private Review createReview() {
        Review review = new Review();
        review.setId(1L);
        review.setCreationDate(LocalDate.ofEpochDay(0L));
        review.setContent("test");
        review.setMovie(movie);
        review.setAuthor(userEntity);
        return review;
    }

    @Before
    public void init() {
        userEntity = new UserEntity(1L, "test", "test", "test");
        movie = new Movie();
        movie.setId(1L);
        movie.setDescription("test");
        movie.setReleaseDate(LocalDate.ofEpochDay(0L));
        movie.setDirector("test");
        movie.setTitle("test");
        movie.setAuthor(new UserEntity(1L, "test", "test", "test"));
    }

    @Test
    @Transactional
    public void shouldGetAllReviews() throws Exception {
        Review review = createReview();
        reviewRepository.saveAndFlush(review);


        restReviewMockMvc.perform(get("/api/reviews"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$..[0].id").exists())
                .andExpect(jsonPath("$..[0].creationDate").exists())
                .andExpect(jsonPath("$..[0].content").exists())
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @Transactional
    public void shouldGetMovie() throws Exception {
        Review review = createReview();

        reviewRepository.saveAndFlush(review);

        restReviewMockMvc.perform(get("/api/reviews/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.creationDate").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @Transactional
    public void shouldDeleteMovie() throws Exception {
        Review review = createReview();
        reviewRepository.saveAndFlush(review);

        restReviewMockMvc.perform(delete("/api/reviews/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional
    public void shouldCreateReview() throws Exception {
        Review review = createReview();


        restReviewMockMvc.perform(post("/api/reviews")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(json.writeValueAsString(review)))
                .andExpect(status().isCreated())
                .andDo(print());
    }




    @Test
    @Transactional
    public void shouldLikeReview() throws Exception {
        Review review = createReview();
        review.setLikedReviews(new LinkedList<>());
        review.getLikedReviews().add(new UserEntity(1L, "1", "1", "1"));
        reviewRepository.saveAndFlush(review);

        restReviewMockMvc.perform(get("/api/reviews/like/1/1"))
                .andExpect(status().isAccepted())
                .andDo(print());
    }


}
