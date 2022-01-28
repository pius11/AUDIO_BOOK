package com.dimata.demo.audiobook.demo_audio_book.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.var;

import com.dimata.demo.audiobook.demo_audio_book.services.crude.RatingHistoryCrude;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import com.dimata.demo.audiobook.demo_audio_book.models.table.RatingHistory;
import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.core.search.SelectQBuilder;
import com.dimata.demo.audiobook.demo_audio_book.core.search.WhereQuery;
import com.dimata.demo.audiobook.demo_audio_book.forms.RatingHistoryForm;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RatingHistoryApi {
    @Autowired
    private RatingHistoryCrude RatingHistoryCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<RatingHistory> createRatingHistory(RatingHistoryForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            RatingHistoryCrude.Option option = RatingHistoryCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(RatingHistoryCrude::create);
    }

    public Flux<RatingHistory> getAllRatingahistory(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(RatingHistory.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(RatingHistory::fromRow)
            .all();
    }
    

    public Mono<RatingHistory> getRatingHistory(Long id) {
        var sql = SelectQBuilder.emptyBuilder(RatingHistory.TABLE_NAME)
            .addWhere(WhereQuery.when(RatingHistory.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(RatingHistory::fromRow)
            .one();
    }

    public Mono<RatingHistory> updateRatingHistory(Long id, RatingHistoryForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                RatingHistoryCrude.Option option = RatingHistoryCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(RatingHistoryCrude::updateRecord);
    }

}
