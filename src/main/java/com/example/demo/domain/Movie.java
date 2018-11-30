package com.example.demo.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    @Id
    private Long id;

    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String title;

    @Size(min = 1, max = 2000)
    @Column(length = 2000, nullable = false)
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String director;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="movie_likes",
            joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="author_id"))
    private List<UserEntity> likedMovies;

    @ManyToOne
    private UserEntity author;


}
