package com.dimata.demo.audiobook.demo_audio_book.services.crude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dimata.demo.audiobook.demo_audio_book.services.dbHandler.UserDbHandler;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



import com.dimata.demo.audiobook.demo_audio_book.models.table.User;

@Component
public class UserCrude {
    @Autowired
    private UserDbHandler userDbHandler;

    public static Option initOption(User record) {
        return new Option(record);
    }

    public Mono<User> create(Option option) {
        return Mono.just(option)
            .flatMap(this::createRecord)
            .map(o -> o.getRecord());
    }

    public Flux<User> createInBatch(Flux<Option> option) {
		return option
			.flatMap(this::create);
	}

    private Mono<Option> createRecord(Option option) {
		return Mono.just(option)
			.flatMap(o -> {
				Mono<User> savedRecord = userDbHandler.create(o.getRecord());
				
				return Mono.zip(savedRecord, Mono.just(o))
					.map(z -> z.getT2().setIdRecord(z.getT1().getId()));
			});
	}

    public Mono<User> updateRecord(Option option) {
        return userDbHandler.update(option.getRecord(), option.getRecord().getId());
    }

    @Data
    @Setter(AccessLevel.NONE)
    public static class Option {
        private final User record;
        
        public Option(User record) {
            this.record = record;
        }

        public Option setIdRecord(long id) {
            record.setId(id);
            return this;
        }
    }
}
