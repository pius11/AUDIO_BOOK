package com.dimata.demo.audiobook.demo_audio_book.services.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.core.search.SelectQBuilder;
import com.dimata.demo.audiobook.demo_audio_book.core.search.WhereQuery;
import com.dimata.demo.audiobook.demo_audio_book.forms.UserForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.User;
import com.dimata.demo.audiobook.demo_audio_book.services.crude.UserCrude;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserApi {
    @Autowired
    private UserCrude userCrude;
    @Autowired
    private R2dbcEntityTemplate template;
    public Mono<User> createUser(UserForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            UserCrude.Option option = UserCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(userCrude::create);
    }

    public Flux<User> getAllUser(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(User.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(User::fromRow)
            .all();
    }

    public Mono<User> getUser(Long id) {
        var sql = SelectQBuilder.emptyBuilder(User.TABLE_NAME)
            .addWhere(WhereQuery.when(User.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(User::fromRow)
            .one();
    }

    
    

    public Mono<User> updateUser(Long id, UserForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                UserCrude.Option option = UserCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(userCrude::updateRecord);
    }

    
}
