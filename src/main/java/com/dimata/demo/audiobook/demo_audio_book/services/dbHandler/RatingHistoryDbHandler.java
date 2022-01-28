package com.dimata.demo.audiobook.demo_audio_book.services.dbHandler;

import com.dimata.demo.audiobook.demo_audio_book.core.api.DbHandlerBase;
import com.dimata.demo.audiobook.demo_audio_book.core.search.CollumnQuery;
import com.dimata.demo.audiobook.demo_audio_book.core.search.SelectQBuilder;
import com.dimata.demo.audiobook.demo_audio_book.core.search.WhereQuery;
import com.dimata.demo.audiobook.demo_audio_book.forms.RatingHistoryForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.RatingHistory;
import com.dimata.demo.audiobook.demo_audio_book.services.repo.RatingHistoryRepo;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class RatingHistoryDbHandler extends DbHandlerBase <RatingHistory, Long>{
    @Autowired
    private RatingHistoryRepo repo;
    @Autowired
    private R2dbcEntityTemplate template;

    @Override
    protected Mono<RatingHistory> setGenerateId(RatingHistory record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }
    
    public Mono<RatingHistory> getAllRatingHistory(RatingHistoryForm form){
        var sql = SelectQBuilder.emptyBuilder(RatingHistory.TABLE_NAME)
        .addColumn(CollumnQuery.add(RatingHistory.RATING_COL).as("TOTAL_RATING"))
        .addWhere(WhereQuery.when(RatingHistory.ID_BOOK).is(form.getId_book()))
        .build();
        return template.getDatabaseClient()
        .sql(sql)
        .map(RatingHistory::fromRow)
        .one();
        
    }


    @Override
    protected Flux<RatingHistory> setGenerateIdBatch(Flux<RatingHistory> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }

    @Override
    protected R2dbcRepository<RatingHistory, Long> getRepository() {
        return repo;
        
    }
}
