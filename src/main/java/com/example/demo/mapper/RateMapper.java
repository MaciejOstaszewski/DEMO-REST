package com.example.demo.mapper;

import com.example.demo.domain.Rate;
import com.example.demo.dto.RateDTO;
import org.springframework.stereotype.Component;

@Component
public class RateMapper implements Mapper<Rate, RateDTO> {
    @Override
    public Rate toEntity(RateDTO rateDTO) {
        Rate rate = new Rate();
        rate.setId(rateDTO.getId());
        rate.setRate(rateDTO.getRate());
        rate.setAuthor(rateDTO.getAuthor());
        rate.setMovie(rateDTO.getMovie());
        return rate;
    }

    @Override
    public RateDTO toDto(Rate rate) {
        RateDTO dto = new RateDTO();
        dto.setId(rate.getId());
        dto.setRate(rate.getRate());
        dto.setAuthorId(rate.getAuthor().getId());
        dto.setMovieId(rate.getAuthor().getId());
        return dto;
    }
}
