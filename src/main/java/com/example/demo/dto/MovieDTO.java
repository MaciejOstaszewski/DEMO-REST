package com.example.demo.dto;

import com.example.demo.domain.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private String director;
    private Long authorId;
    private UserEntity author;

    public MovieDTO(Long id, String title, String description, LocalDate releaseDate, String director, Long authorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.authorId = authorId;
    }

    private List<UserEntity> movieLikes = new ArrayList<>();

}
