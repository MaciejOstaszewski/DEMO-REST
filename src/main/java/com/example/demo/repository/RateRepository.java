package com.example.demo.repository;

import com.example.demo.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findOneById(Long id);
}
