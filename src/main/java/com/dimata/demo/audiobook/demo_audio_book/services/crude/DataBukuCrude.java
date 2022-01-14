package com.dimata.demo.audiobook.demo_audio_book.services.crude;

import com.dimata.demo.audiobook.demo_audio_book.models.table.DataBuku;
import com.dimata.demo.audiobook.demo_audio_book.services.dbHandler.DataBukuDbhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DataBukuCrude {
    @Autowired
    private DataBukuDbhandler DataBukuDbHandler;

    public Option initOption(DataBuku record) {
        return new Option(record);
    }

    public Mono<DataBuku> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<DataBuku> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<DataBuku> savedRecord = DataBukuDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<DataBuku> updateRecord(Option option) {
        return DataBukuDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final DataBuku record;
        
        public Option(DataBuku record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
