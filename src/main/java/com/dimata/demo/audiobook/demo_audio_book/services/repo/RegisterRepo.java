package com.dimata.demo.audiobook.demo_audio_book.services.repo;

import com.dimata.demo.audiobook.demo_audio_book.models.table.Register;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface RegisterRepo extends R2dbcRepository<Register, Long> {
    
    Mono<Register> findById(Long id);
}
