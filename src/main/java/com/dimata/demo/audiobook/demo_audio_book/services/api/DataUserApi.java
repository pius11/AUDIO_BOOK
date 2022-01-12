package com.dimata.demo.sekolah.demo_data_siswa.services.api;


import com.dimata.demo.sekolah.demo_data_siswa.core.search.CommonParam;
import com.dimata.demo.sekolah.demo_data_siswa.core.search.SelectQBuilder;
import com.dimata.demo.sekolah.demo_data_siswa.core.search.WhereQuery;
import com.dimata.demo.sekolah.demo_data_siswa.forms.DataUserForm;
import com.dimata.demo.sekolah.demo_data_siswa.models.table.DataUser;
import com.dimata.demo.sekolah.demo_data_siswa.services.crude.DataUserCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class DataSekolahApi {
    @Autowired
    private DataUserCrude dataSekolahCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<DataUser> createDataSekolah(DataUserForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            DataUserCrude.Option option = DataUserCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(dataSekolahCrude::create);
    }

    public Flux<DataUser> getAllDataSekolah(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(DataUser.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(DataUser::fromRow)
            .all();
    }

    public Mono<DataUser> getDataSekolah(Long id) {
        var sql = SelectQBuilder.emptyBuilder(DataUser.TABLE_NAME)
            .addWhere(WhereQuery.when(DataUser.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(DataUser::fromRow)
            .one();
    }

    public Mono<DataUser> updateDataSekolah(Long id, DataUserForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                DataUserCrude.Option option = DataUserCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(dataSekolahCrude::updateRecord);
    }    
}
