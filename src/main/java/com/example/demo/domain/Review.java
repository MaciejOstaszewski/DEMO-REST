package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    private Long id;

    @Column(length = 2000, nullable = false)
    private String content;


    @Column(name = "creation_date")
    private LocalDate creationDate;

    @ManyToOne
    private Movie movie;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="review_likes",
            joinColumns=@JoinColumn(name="review_id"),
            inverseJoinColumns=@JoinColumn(name="author_id"))
    private List<UserEntity> likedReviews;

    @ManyToOne
    private UserEntity author;


}
