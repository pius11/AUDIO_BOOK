package com.dimata.demo.audiobook.demo_audio_book.models.response;

import com.dimata.demo.audiobook.demo_audio_book.models.table.DataBuku;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookAndRating {
    private DataBuku book;
    private Double avgRating;
}
