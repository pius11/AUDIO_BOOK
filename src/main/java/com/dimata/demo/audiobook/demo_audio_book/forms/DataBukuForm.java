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
    private String deskripsi;
    private String image;
    private String pengarang;
    private String rating;
    private String countRating;
    private String genre;
        
    
    @Override
    public DataBuku convertNewRecord() {
        return DataBuku.Builder.createNewRecord(judulbuku)
            
            .isibuku(isibuku)
            .deskripsi(deskripsi)
            .image(image)
            .pengarang(pengarang)
            .rating(rating)
            .countRating(countRating)
            .genre(genre)
            .id(id)
            .build();
    }
    @Override
    public DataBuku convertToRecord() {
        return DataBuku .Builder.emptyBuilder()
            
            .judulbuku(judulbuku)
            .image(image)
            .pengarang(pengarang)
            .isibuku(isibuku)
            .deskripsi(deskripsi)
            .genre(genre)
            .id(id)
            .build();
    }
}
