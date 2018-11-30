package com.example.demo.mapper;

import com.example.demo.domain.Review;
import com.example.demo.dto.ReviewDTO;
import org.springframework.stereotype.Component;


@Component
public class ReviewMapper implements Mapper<Review, ReviewDTO>{


    @Override
    public Review toEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setContent(reviewDTO.getContent());
        review.setCreationDate(reviewDTO.getCreationDate());
        return review;
    }

    @Override
    public ReviewDTO toDto(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setAuthor(review.getAuthor());
        dto.setContent(review.getContent());
        dto.setCreationDate(review.getCreationDate());
        dto.setMovie(review.getMovie());
        return dto;
    }
}
