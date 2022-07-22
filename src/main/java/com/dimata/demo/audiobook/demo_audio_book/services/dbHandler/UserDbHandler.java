package com.dimata.demo.audiobook.demo_audio_book.services.dbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;

import com.dimata.demo.audiobook.demo_audio_book.core.api.DbHandlerBase;
import com.dimata.demo.audiobook.demo_audio_book.models.table.User;
import com.dimata.demo.audiobook.demo_audio_book.services.repo.UserRepo;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class UserDbHandler extends DbHandlerBase<User,Long>{
    @Autowired
    private UserRepo repo;

    @Override
    protected Mono<User> setGenerateId(User record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }

    @Override
    protected Flux<User> setGenerateIdBatch(Flux<User> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }

    @Override
    protected R2dbcRepository<User, Long> getRepository() {
        return repo;
    }
}