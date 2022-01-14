package com.dimata.demo.audiobook.demo_audio_book.services.dbHandler;

import com.dimata.demo.audiobook.demo_audio_book.core.api.DbHandlerBase;
import com.dimata.demo.audiobook.demo_audio_book.models.table.DataBuku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class DataBukuDbhandler extends DbHandlerBase<DataBuku, Long>{
    @Autowired
    private R2dbcRepository<DataBuku, Long> repo;

    @Override
    protected R2dbcRepository<DataBuku, Long> getRepository() {
        return repo;
    }

    @Override
    protected Mono<DataBuku> setGenerateId(DataBuku record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<DataBuku> setGenerateIdBatch(Flux<DataBuku> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }
}
