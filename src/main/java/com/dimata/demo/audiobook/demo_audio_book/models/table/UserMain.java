package com.dimata.demo.audiobook.demo_audio_book.models.table;

import static com.dimata.demo.audiobook.demo_audio_book.core.util.ManipulateUtil.changeItOrNot;

import com.dimata.demo.audiobook.demo_audio_book.core.api.UpdateAvailable;
import com.dimata.demo.audiobook.demo_audio_book.core.util.GenerateUtil;
import com.dimata.demo.audiobook.demo_audio_book.core.util.ManipulateUtil;
// import com.dimata.demo.audiobook.demo_audio_book.core.util.ManipulateUtil;
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
public class UserMain implements UpdateAvailable<UserMain>, Persistable<Long> {
    public static final String TABLE_NAME = "user_main ";
    public static final String ID_COL = "id_main";
    public static final String REGISTER_CODE_COL = "register_code";
    public static final String USER_CODE_COL = "user_code";
    public static final String ID_BOOK_COL = "id_book";
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private Long registerCode;
        private Long userCode;
        private Long idBook;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord() {
            return new Builder().newRecord(true);
                
                
        }

        public static Builder updateBuilder(UserMain oldRecord, UserMain  newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .registerCode(changeItOrNot(newRecord.getRegisterCode(), oldRecord.getRegisterCode()))
                .userCode(changeItOrNot(newRecord.getUserCode(), oldRecord.getUserCode()))
                .idBook(changeItOrNot(newRecord.getIdBook(), oldRecord.getIdBook()));
                
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public UserMain  build() {
            UserMain  result = new UserMain ();
            result.setId(id);
            result.setUserCode(userCode);
            result.setRegisterCode(registerCode);
            result.setIdBook(idBook);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private Long registerCode;
    private Long userCode;
    private Long idBook;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static UserMain  fromRow(Row row) {
        var result = new UserMain();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setRegisterCode(ManipulateUtil.parseRow(row, REGISTER_CODE_COL, Long.class));
        result.setUserCode(ManipulateUtil.parseRow(row, USER_CODE_COL, Long.class));
        result.setIdBook(ManipulateUtil.parseRow(row, ID_BOOK_COL, Long.class));
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
    public UserMain  update(UserMain newData) {
        return Builder.updateBuilder(this, newData).build();
    }

   
    
}
