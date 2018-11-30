package com.example.demo.dto;

import com.example.demo.domain.Movie;
import com.example.demo.domain.UserEntity;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ReviewDTO {
    private Long id;

    private String content;

    private LocalDate creationDate;

    private Long movieId;

    private Long authorId;

    private UserEntity author;

    private Movie movie;
}
