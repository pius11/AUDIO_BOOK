package com.dimata.demo.audiobook.demo_audio_book.forms;

import com.dimata.demo.audiobook.demo_audio_book.core.api.RecordAdapter;
import com.dimata.demo.audiobook.demo_audio_book.enums.StatusRating;
import com.dimata.demo.audiobook.demo_audio_book.models.table.RatingHistory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingHistoryForm implements RecordAdapter<RatingHistory> {
    private Long id;
    private String userCode;
    private Long id_book;
    private String ulasan;
    private String rating;
    private StatusRating ratingStatus;
    @Override
    public RatingHistory convertNewRecord() {
        return RatingHistory.Builder.createNewRecord()
            
            .userCode(userCode)
            
            .ulasan(ulasan)
            .rating(rating)
            .id_book(id_book)
            .id(id)
            .build();
    }
    @Override
    public RatingHistory convertToRecord() {
        return RatingHistory.Builder.emptyBuilder()
            .userCode(userCode)
            
            .ulasan(ulasan)
            .id_book(id_book)
            .rating(rating)
            .ratingStatus(ratingStatus)
            .id(id)
            .build();
    }
    
}
