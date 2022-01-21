package com.dimata.demo.audiobook.demo_audio_book.services.dbHandler;

import com.dimata.demo.audiobook.demo_audio_book.core.api.DbHandlerBase;
import com.dimata.demo.audiobook.demo_audio_book.models.table.UserMain;
import com.dimata.demo.audiobook.demo_audio_book.services.repo.UserMainRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;
import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class UserMainDbHandler extends DbHandlerBase<UserMain, Long>{
    @Autowired
    private UserMainRepo repo;

    @Override
    protected Mono<UserMain> setGenerateId(UserMain record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<UserMain> setGenerateIdBatch(Flux<UserMain> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }

    @Override
    protected R2dbcRepository<UserMain, Long> getRepository() {
        
        return repo;
    }

}
