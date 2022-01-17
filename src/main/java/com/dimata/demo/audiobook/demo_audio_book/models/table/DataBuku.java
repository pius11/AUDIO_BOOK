package com.dimata.demo.audiobook.demo_audio_book.models.table;

import static com.dimata.demo.audiobook.demo_audio_book.core.util.ManipulateUtil.changeItOrNot;


import java.util.Objects;

import com.dimata.demo.audiobook.demo_audio_book.core.api.UpdateAvailable;
import com.dimata.demo.audiobook.demo_audio_book.core.util.GenerateUtil;
import com.dimata.demo.audiobook.demo_audio_book.core.util.ManipulateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import io.r2dbc.spi.Row;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class DataBuku implements UpdateAvailable<DataBuku>, Persistable <Long>{
    public static final String TABLE_NAME = "data_buku ";
    public static final String ID_COL = "id_book";
    public static final String JUDUL_BUKU_COL = "judul_buku";
    public static final String ISI_BUKU_COL = "isi_buku";
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private String judulbuku;
        private String isibuku;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(String judulbuku) {
            return new Builder().newRecord(true)
                .judulbuku(Objects.requireNonNull(judulbuku, "judul buku diperlukan"));
                
                
        }

        public static Builder updateBuilder(DataBuku oldRecord, DataBuku  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .isibuku(changeItOrNot(newRecord.getIsibuku(), oldRecord.getIsibuku()))
                .judulbuku(changeItOrNot(newRecord.getJudulbuku(), oldRecord.getJudulbuku()));
                
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public DataBuku  build() {
            DataBuku  result = new DataBuku ();
            result.setId(id);
            result.setIsibuku(isibuku);
            result.setJudulbuku(judulbuku);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private String judulbuku;
    private String isibuku;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static DataBuku  fromRow(Row row) {
        var result = new DataBuku ();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setJudulbuku(ManipulateUtil.parseRow(row, JUDUL_BUKU_COL, String.class));
        result.setIsibuku(ManipulateUtil.parseRow(row, ISI_BUKU_COL, String.class));
        return result;
    }

    @Override
    public boolean isNew() {
        if (id == null && insertId == null) {
            id = new GenerateUtil().generateOID();
            return true;
        } else if (id == null) {
            id = insertId;
            return true;
        }
        return false;
    }

    @Override
    public DataBuku  update(DataBuku  newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
