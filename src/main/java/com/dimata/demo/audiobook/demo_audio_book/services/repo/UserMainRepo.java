package com.dimata.demo.audiobook.demo_audio_book.services.repo;

import com.dimata.demo.audiobook.demo_audio_book.models.table.UserMain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface UserMainRepo extends R2dbcRepository<UserMain, Long> {
    Mono<UserMain> finById(Long id);
}
