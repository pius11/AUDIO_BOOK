package com.dimata.demo.audiobook.demo_audio_book.models.table;

import static com.dimata.demo.audiobook.demo_audio_book.core.util.ManipulateUtil.changeItOrNot;
import org.springframework.data.annotation.Transient;

import com.dimata.demo.audiobook.demo_audio_book.core.api.UpdateAvailable;
import com.dimata.demo.audiobook.demo_audio_book.core.util.GenerateUtil;
import com.dimata.demo.audiobook.demo_audio_book.core.util.ManipulateUtil;
import com.dimata.demo.audiobook.demo_audio_book.enums.StatusRating;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import ch.qos.logback.core.status.Status;
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
public class RatingHistory implements UpdateAvailable<RatingHistory>, Persistable<Long>{
    
    public static final String TABLE_NAME = "rating_history";
    public static final String ID_COL = "id_history";
    public static final String ID_BOOK = "id_book";
    public static final String USER_CODE_COL = "user_code";
    public static final String ULASAN_COL = "ulasan";
    public static final String RATING_COL = "rating";
    public static final String RATING_STATUS_COL = "rating_status";
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private String userCode;
        private String ulasan;
        private String rating;
        private Long id_book;
        private StatusRating ratingStatus;
        

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord() {
            return new Builder().newRecord(true);
            
        }

        public static Builder updateBuilder(RatingHistory oldRecord, RatingHistory newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                
                .userCode(changeItOrNot(newRecord.getUserCode(), oldRecord.getUserCode()))
                .rating(changeItOrNot(newRecord.getRating(), oldRecord.getRating()))
                .ratingStatus(changeItOrNot(newRecord.getStatusRating(), oldRecord.getStatusRating()))
                .id_book(changeItOrNot(newRecord.getId_book(), oldRecord.getId_book()))
                .ulasan(changeItOrNot(newRecord.getUlasan(), oldRecord.getUlasan()));
                
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public RatingHistory build() {
            RatingHistory result = new RatingHistory();
            result.setId(id);
            result.setUserCode(userCode);
            result.setUlasan(ulasan);
            result.setRating(rating);
            result.setId_book(id_book);
            result.setRatingStatus(ratingStatus);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private String userCode;
    private String ulasan;
    private String rating;
    private String ratingStatus;
    private Long id_book;
    @Transient
    @JsonIgnore
    private Long insertId;

    public void setRatingStatus(StatusRating statusRating) {
        if (statusRating != null) {
            this.ratingStatus = statusRating.getCode();
        }
    }

    public StatusRating getStatusRating() {
        if (ratingStatus != null) {
            return StatusRating.getStatus(this.ratingStatus);
        }
        return null;
    }

    

    public static RatingHistory fromRow(Row row) {
        var result = new RatingHistory();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setUlasan(ManipulateUtil.parseRow(row, ULASAN_COL, String.class));
        result.setRating(ManipulateUtil.parseRow(row, RATING_COL, String.class));
        result.setRatingStatus(StatusRating.getStatus(ManipulateUtil.parseRow(row, RATING_STATUS_COL, String.class)));
        result.setUserCode(ManipulateUtil.parseRow(row, USER_CODE_COL, String.class));
        result.setId_book(ManipulateUtil.parseRow(row, ID_BOOK, Long.class));
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
    public RatingHistory update(RatingHistory newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
