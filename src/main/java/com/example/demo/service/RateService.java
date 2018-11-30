package com.example.demo.service;

import com.example.demo.domain.Rate;
import com.example.demo.dto.RateDTO;
import com.example.demo.mapper.RateMapper;
import com.example.demo.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;
    private final UserService userService;
    private final MovieService movieService;
    private final RateMapper rateMapper;


    public RateDTO addRate(RateDTO rateDTO) {
        Rate rate = rateMapper.toEntity(rateDTO);
        userService.getMaybeUser(rateDTO.getAuthorId()).ifPresent(rate::setAuthor);
        movieService.getMovieEntity(rateDTO.getMovieId()).ifPresent(rate::setMovie);
        return rateMapper.toDto(rateRepository.save(rate));

    }
}
