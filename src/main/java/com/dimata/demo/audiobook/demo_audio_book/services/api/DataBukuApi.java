package com.dimata.demo.audiobook.demo_audio_book.services.api;

import com.dimata.demo.audiobook.demo_audio_book.core.search.CommonParam;
import com.dimata.demo.audiobook.demo_audio_book.core.search.SelectQBuilder;
import com.dimata.demo.audiobook.demo_audio_book.core.search.WhereQuery;
import com.dimata.demo.audiobook.demo_audio_book.forms.DataBukuForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.DataBuku;
import com.dimata.demo.audiobook.demo_audio_book.services.crude.DataBukuCrude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataBukuApi {
    @Autowired
    private DataBukuCrude DataBukuCrude;
    @Autowired
	private R2dbcEntityTemplate template;

    public Mono<DataBuku> createDataBuku(DataBukuForm form) {
        return Mono.just(form)
        .flatMap(f -> {
            DataBukuCrude.Option option = DataBukuCrude.initOption(f.convertNewRecord());
            return Mono.just(option);
        })
        .flatMap(DataBukuCrude::create);
    }

    public Flux<DataBuku> getAllDataBuku(CommonParam param) {
        var sql = SelectQBuilder.builderWithCommonParam(DataBuku.TABLE_NAME, param)
            .build();
        return template.getDatabaseClient()
            .sql(sql)
            .map(DataBuku::fromRow)
            .all();
    }

    public Mono<DataBuku> getDataBuku(Long id) {
        var sql = SelectQBuilder.emptyBuilder(DataBuku.TABLE_NAME)
            .addWhere(WhereQuery.when(DataBuku.ID_COL).is(id))
            .build();
        System.out.println(sql);
        return template.getDatabaseClient()
            .sql(sql)
            .map(DataBuku::fromRow)
            .one();
    }

    public Mono<DataBuku> updateDataBuku(Long id, DataBukuForm form) {
        return Mono.zip(Mono.just(id), Mono.just(form))
            .map(z -> {
                z.getT2().setId(z.getT1());
                return z.getT2();
            })
            .flatMap(d -> {
                DataBukuCrude.Option option = DataBukuCrude.initOption(d.convertNewRecord());
                return Mono.just(option);
            })
            .flatMap(DataBukuCrude::updateRecord);
    }
}
