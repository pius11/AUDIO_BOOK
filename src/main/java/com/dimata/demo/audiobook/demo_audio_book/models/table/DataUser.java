package com.dimata.demo.audiobook.demo_audio_book.models.table;



import static com.dimata.demo.audiobook.demo_audio_book.core.util.ManipulateUtil.changeItOrNot;

import java.time.LocalDate;
import java.util.Objects;

import com.dimata.demo.audiobook.demo_audio_book.core.api.UpdateAvailable;
import com.dimata.demo.audiobook.demo_audio_book.core.util.GenerateUtil;
import com.dimata.demo.audiobook.demo_audio_book.core.util.ManipulateUtil;
import com.dimata.demo.audiobook.demo_audio_book.core.util.jackson.DateSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

public class DataUser implements UpdateAvailable<DataUser>, Persistable<Long>{
    public static final String TABLE_NAME = "data_user";
    public static final String ID_COL = "user_code";
    public static final String EMAIL_COL = "email";
    public static final String DISPLAYNAME_COL = "displayname";
    public static final String PHONENUM_COL = "phoneNum";
    public static final String USERNAME_COL = "username";
    public static final String BIRTH_DATE_COL = "birth_date";
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private String email;
        private LocalDate birthDate;
        private String displayname;
        private String username;
        private String phonenum;


        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(String email) {
            return new Builder().newRecord(true)
                .email(Objects.requireNonNull(email, "EMAIL diperlukan"));
                
        }

        public static Builder updateBuilder(DataUser oldRecord, DataUser newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .birthDate(changeItOrNot(newRecord.getBirthDate(), oldRecord.getBirthDate()))
                .displayname(changeItOrNot(newRecord.getDisplayname(), oldRecord.getDisplayname()))
                .username(changeItOrNot(newRecord.getUsername(), oldRecord.getUsername()))
                .phonenum(changeItOrNot(newRecord.getPhonenum(), oldRecord.getPhonenum()));

        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public DataUser build() {
            DataUser result = new DataUser();
            
            result.setBirthDate(birthDate);
            result.setEmail(email);
            result.setDisplayname(displayname);
            result.setUsername(username);
            result.setPhonenum(phonenum);

            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private String email;
    private String displayname;
    private String username;
    private String phonenum;

    @JsonSerialize(converter = DateSerialize.class)
    private LocalDate birthDate;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static DataUser fromRow(Row row) {
        var result = new DataUser();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setEmail(ManipulateUtil.parseRow(row, EMAIL_COL, String.class));
        result.setBirthDate(ManipulateUtil.parseRow(row, BIRTH_DATE_COL, LocalDate.class));
        result.setDisplayname(ManipulateUtil.parseRow(row, DISPLAYNAME_COL, String.class));
        result.setUsername(ManipulateUtil.parseRow(row, USERNAME_COL, String.class));
        result.setPhonenum(ManipulateUtil.parseRow(row, PHONENUM_COL, String.class));

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
    public DataUser update(DataUser newData) {
        return Builder.updateBuilder(this, newData).build();
    }
}
