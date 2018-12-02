package com.example.demo.service;


import com.example.demo.domain.Review;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.mapper.ReviewMapper;
import com.example.demo.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final UserService userService;
    private final ReviewMapper reviewMapper;

    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviewMapper.toDtos(reviews);
    }

    public Optional<ReviewDTO> getReview(Long id) {
        return reviewRepository.findOneById(id).map(reviewMapper::toDto);
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        movieService.getMovieEntity(reviewDTO.getMovieId()).ifPresent(review::setMovie);
        userService.getMaybeUser(reviewDTO.getAuthorId()).ifPresent(review::setAuthor);
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    public void deleteReview(Long id) {
        reviewRepository.findOneById(id).ifPresent(review -> {
            reviewRepository.deleteById(id);
        });
    }



    public ReviewDTO likeReview(Long userId, Long reviewId) {
        Review review = reviewRepository.getOne(reviewId);
        review.getLikedReviews().add(userService.getUserEntity(userId));

        return reviewMapper.toDto(reviewRepository.save(review));
    }
}
