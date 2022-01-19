package com.dimata.demo.audiobook.demo_audio_book.forms;

import com.dimata.demo.audiobook.demo_audio_book.core.api.RecordAdapter;
import com.dimata.demo.audiobook.demo_audio_book.models.table.DataBuku;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataBukuForm implements RecordAdapter<DataBuku> {
    private Long id;
    private String judulbuku;
    private String isibuku;
    private String image;
        
    
    @Override
    public DataBuku convertNewRecord() {
        return DataBuku.Builder.createNewRecord(judulbuku)
            
            .isibuku(isibuku)
            .image(image)
            .id(id)
            .build();
    }
    @Override
    public DataBuku convertToRecord() {
        return DataBuku .Builder.emptyBuilder()
            
            .judulbuku(judulbuku)
            .image(image)
            .isibuku(isibuku)
            .id(id)
            .build();
    }
}
