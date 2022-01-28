package com.dimata.demo.audiobook.demo_audio_book.services.api;
import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.core.search.SelectQBuilder;
import com.dimata.demo.audiobook.demo_audio_book.core.search.WhereQuery;
import com.dimata.demo.audiobook.demo_audio_book.forms.DataAll;
import com.dimata.demo.audiobook.demo_audio_book.forms.UserMainForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.UserMain;
import com.dimata.demo.audiobook.demo_audio_book.services.crude.DataBukuCrude;
import com.dimata.demo.audiobook.demo_audio_book.services.crude.UserMainCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserMainApi {
    @Autowired
    private UserMainCrude UserMainCrude;
    // @Autowired
    // private DataBukuCrude bukuCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<UserMain> createUserMain(UserMainForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            UserMainCrude.Option option = UserMainCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(UserMainCrude::create);
    }

    // public Mono<UserMain> createRelation(DataAll form){
    //     return Mono.just(form)
    //         .flatMap(f -> {
    //             return Mono.zip(Mono.just(f.getBuku()), Mono.just(f.getUserId()));
    //         })
    //         .flatMap(z -> {
    //             DataBukuCrude.Option option = DataBukuCrude.initOption(z.getT1().convertNewRecord());
    //             return Mono.zip(bukuCrude.create(option), Mono.just(z.getT2()))
    //         })
    // }

    public Flux<UserMain> getAllUserMain(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(UserMain.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(UserMain::fromRow)
            .all();
    }

    public Mono<UserMain> getUserMain(Long id) {
        var sql = SelectQBuilder.emptyBuilder(UserMain.TABLE_NAME)
            .addWhere(WhereQuery.when(UserMain.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(UserMain::fromRow)
            .one();
    }

    public Mono<UserMain> updateUserMain(Long id, UserMainForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                UserMainCrude.Option option = UserMainCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(UserMainCrude::updateRecord);
    }
}
