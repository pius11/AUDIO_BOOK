package com.dimata.demo.sekolah.demo_audio_book.services.repo;

import com.dimata.demo.sekolah.demo_audio_book.models.table.DataUser;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface DataUserRepo extends R2dbcRepository<DataUser, Long> {
    Mono<DataUser> findById(long id);
    
}
