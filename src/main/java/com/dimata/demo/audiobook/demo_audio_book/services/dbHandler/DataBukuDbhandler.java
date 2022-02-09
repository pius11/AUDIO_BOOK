package com.dimata.demo.audiobook.demo_audio_book.services.dbHandler;


import java.util.Arrays;
import java.util.List;

import com.dimata.demo.audiobook.demo_audio_book.core.api.DbHandlerBase;
import com.dimata.demo.audiobook.demo_audio_book.core.search.CollumnQuery;
import com.dimata.demo.audiobook.demo_audio_book.core.search.CollumnStep;
import com.dimata.demo.audiobook.demo_audio_book.core.search.SelectQBuilder;
import com.dimata.demo.audiobook.demo_audio_book.core.search.WhereQuery;
import com.dimata.demo.audiobook.demo_audio_book.forms.DataBukuForm;
import com.dimata.demo.audiobook.demo_audio_book.models.table.DataBuku;
import com.dimata.demo.audiobook.demo_audio_book.services.repo.DataBukuRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Component;


import lombok.EqualsAndHashCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@EqualsAndHashCode(callSuper = true)
public class DataBukuDbhandler extends DbHandlerBase<DataBuku, Long>{
    @Autowired
    private DataBukuRepo repo;
    @Autowired
    private R2dbcEntityTemplate template;

    @Override
    protected Mono<DataBuku> setGenerateId(DataBuku record) {
        return Mono.just(record)
            .map(z -> {
                long id = getGenerateUtil().generateOID();
                z.setInsertId(id);
                return z;
            });
    }
    public Mono<DataBuku> getColumns(DataBukuForm form){
    var sql = SelectQBuilder.emptyBuilder(DataBuku.TABLE_NAME)
        .addColumns(getCollumns())
        .addWhere(WhereQuery.when(DataBuku.ID_COL).is(form.getId()))
        .build();
        return template.getDatabaseClient()
        .sql(sql)
        .map(DataBuku::fromRow)
        .one();
    }

    public String getColName(String colName) {
        return getTableName() + "." + colName;
    }

    public String getTableName() {
        return DataBuku.TABLE_NAME;
    }

    public List<CollumnStep> getCollumns() {
        return Arrays.asList(CollumnQuery.add(getColName(DataBuku.ID_COL)),
            CollumnQuery.add(getColName(DataBuku.IMAGE_COL)),
            CollumnQuery.add(getColName(DataBuku.JUDUL_BUKU_COL)),
            CollumnQuery.add(getColName(DataBuku.DESKRIPSI_COL)),
            CollumnQuery.add(getColName(DataBuku.PENGARANG_COL)),
            CollumnQuery.add(getColName(DataBuku.RATING_COL)),
            CollumnQuery.add(getColName(DataBuku.GENRE_COL)),
CollumnQuery.add(getColName(getColName(DataBuku.ID_COL))),
            CollumnQuery.add(getColName(DataBuku.IMAGE_COL)),
            CollumnQuery.add(getColName(DataBuku.JUDUL_BUKU_COL)),
            CollumnQuery.add(getColName(DataBuku.DESKRIPSI_COL)),
            CollumnQuery.add(getColName(DataBuku.RATING_COL)),
            CollumnQuery.add(getColName(DataBuku.GENRE_COL)),
            CollumnQuery.add(getColName(DataBuku.PENGARANG_COL))
        );
    }
    @Override
    protected Flux<DataBuku> setGenerateIdBatch(Flux<DataBuku> records) {
        return records
            .map(rec -> {
                long id = getGenerateUtil().generateOID();
                rec.setInsertId(id);
                return rec;
            });
    }
    
    @Override
    protected R2dbcRepository<DataBuku, Long> getRepository() {
        
        return repo;
    }
}
