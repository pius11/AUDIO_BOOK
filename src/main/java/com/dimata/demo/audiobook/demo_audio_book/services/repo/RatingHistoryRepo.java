package com.dimata.demo.audiobook.demo_audio_book.services.repo;


import com.dimata.demo.audiobook.demo_audio_book.models.table.RatingHistory;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;
public interface RatingHistoryRepo extends R2dbcRepository<RatingHistory, Long> {
    Mono<RatingHistory> findById(Long id);
}
