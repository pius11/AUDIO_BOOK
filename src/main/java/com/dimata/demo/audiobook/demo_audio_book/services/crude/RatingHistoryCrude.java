package com.dimata.demo.audiobook.demo_audio_book.services.crude;

import com.dimata.demo.audiobook.demo_audio_book.models.table.RatingHistory;
import com.dimata.demo.audiobook.demo_audio_book.services.dbHandler.RatingHistoryDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RatingHistoryCrude {
    @Autowired
    private RatingHistoryDbHandler ratingHistoryDbHandler;

    public static Option initOption(RatingHistory record) {
        return new Option(record);
    }

    public Mono<RatingHistory> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<RatingHistory> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<RatingHistory> savedRecord = ratingHistoryDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<RatingHistory> updateRecord(Option option) {
        return ratingHistoryDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final RatingHistory record;
        
        public Option(RatingHistory record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
