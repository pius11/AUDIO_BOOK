package com.dimata.demo.audiobook.demo_audio_book.services.repo;
import com.dimata.demo.audiobook.demo_audio_book.models.table.DataBuku;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

public interface DataBukuRepo extends R2dbcRepository<DataBuku, Long> {
    // Mono<DataBuku> findById(long id);


     @Query("SELECT image FROM data_buku WHERE id_book= $1")
     Mono<DataBuku> findById(Long id);
}
