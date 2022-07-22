package com.dimata.demo.audiobook.demo_audio_book.services.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.dimata.demo.audiobook.demo_audio_book.models.table.User;

import reactor.core.publisher.Mono;

public interface UserRepo extends R2dbcRepository <User,Long>{
    Mono<User> findById(Long id);
}
