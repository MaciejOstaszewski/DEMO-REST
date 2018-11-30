package com.example.demo.dto;

import com.example.demo.domain.Movie;
import com.example.demo.domain.UserEntity;
import lombok.Data;

@Data
public class RateDTO {

    private Long id;

    private Integer rate;

    private Long movieId;

    private Long authorId;

    private UserEntity author;

    private Movie movie;
}
