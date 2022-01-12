package com.dimata.demo.sekolah.demo_audio_book.services.api;

import com.dimata.demo.sekolah.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.sekolah.demo_audio_book.core.search.SelectQBuilder;
import com.dimata.demo.sekolah.demo_audio_book.core.search.WhereQuery;
import com.dimata.demo.sekolah.demo_audio_book.forms.RegisterForm;
import com.dimata.demo.sekolah.demo_audio_book.models.table.Register;
import com.dimata.demo.sekolah.demo_audio_book.services.crude.RegisterCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RegisterApi {
    
    @Autowired
    private RegisterCrude dataSiswaCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<Register> createRegister(RegisterForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            RegisterCrude.Option option = RegisterCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(dataSiswaCrude::create);
    }

    public Flux<Register> getAllRegister(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(Register.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(Register::fromRow)
            .all();
    }

    public Mono<Register> getRegister(Long id) {
        var sql = SelectQBuilder.emptyBuilder(Register.TABLE_NAME)
            .addWhere(WhereQuery.when(Register.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(Register::fromRow)
            .one();
    }

    public Mono<Register> updateRegister(Long id, RegisterForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                RegisterCrude.Option option = RegisterCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(dataSiswaCrude::updateRecord);
    }
}
