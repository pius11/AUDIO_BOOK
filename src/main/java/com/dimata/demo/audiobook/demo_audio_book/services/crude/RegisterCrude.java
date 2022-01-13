package com.dimata.demo.audiobook.demo_audio_book.services.crude;

import com.dimata.demo.audiobook.demo_audio_book.models.table.Register;
import com.dimata.demo.audiobook.demo_audio_book.services.dbHandler.RegisterDbHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RegisterCrude {
    
    @Autowired
    private RegisterDbHandler RegisterDbHandler;

    public static Option initOption(Register record) {
        return new Option(record);
    }

    public Mono<Register> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<Register> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<Register> savedRecord = RegisterDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<Register> updateRecord(Option option) {
        return RegisterDbHandler.updateOnly(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final Register record;
        
        public Option(Register record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
