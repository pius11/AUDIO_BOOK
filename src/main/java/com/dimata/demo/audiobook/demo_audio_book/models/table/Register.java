package com.dimata.demo.sekolah.demo_audio_book.models.table;

import static com.dimata.demo.sekolah.demo_audio_book.core.util.ManipulateUtil.changeItOrNot;

import java.time.LocalDate;
import java.util.Objects;

import com.dimata.demo.sekolah.demo_audio_book.core.api.UpdateAvailable;
import com.dimata.demo.sekolah.demo_audio_book.core.util.GenerateUtil;
import com.dimata.demo.sekolah.demo_audio_book.core.util.ManipulateUtil;
import com.dimata.demo.sekolah.demo_audio_book.core.util.jackson.DateSerialize;
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
public class Register implements UpdateAvailable<Register>, Persistable<Long>{
    
    public static final String TABLE_NAME = "register";
    public static final String ID_COL = "register_code";
    public static final String EMAIL_COL = "email";
    public static final String PASSWORD_COL = "password";
    public static final String BIRTH_DATE_COL = "birth_date";
    

    @Accessors(fluent = true)
    @Setter
    public static class Builder {

        private Long  id;
        private String email;
        private String password;
        private LocalDate birthDate;

        @Setter(AccessLevel.PRIVATE)
        private boolean newRecord = false;

        public static Builder createNewRecord(String email, String password) {
            return new Builder().newRecord(true)
                .email(Objects.requireNonNull(email, "EMAIL diperlukan"))
                .password(Objects.requireNonNull(password, "Password tidak boleh kosong"));
                
        }

        public static Builder updateBuilder(Register oldRecord, Register newRecord) {
            return new Builder()
                .id(oldRecord.getId())
                .birthDate(changeItOrNot(newRecord.getBirthDate(), oldRecord.getBirthDate()))
                .password(changeItOrNot(newRecord.getPassword(), oldRecord.getPassword()))
                .email(changeItOrNot(newRecord.getEmail(), oldRecord.getEmail()));
                
        }

        public static Builder emptyBuilder() {
            return new Builder();
        }

        public Register build() {
            Register result = new Register();
            
            result.setPassword(password);
            result.setBirthDate(birthDate);
            result.setEmail(email);
            return result;
        }
    }

    @Id
    @Column(ID_COL)
    private Long id;
    private String email;
    private String password;
    @JsonSerialize(converter = DateSerialize.class)
    private LocalDate birthDate;
    @Transient
    @JsonIgnore
    private Long insertId;

    

    public static Register fromRow(Row row) {
        var result = new Register();
        result.setId(ManipulateUtil.parseRow(row, ID_COL, Long.class));
        result.setEmail(ManipulateUtil.parseRow(row, EMAIL_COL, String.class));
        result.setPassword(ManipulateUtil.parseRow(row, PASSWORD_COL, String.class));
        result.setBirthDate(ManipulateUtil.parseRow(row, BIRTH_DATE_COL, LocalDate.class));
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
    public Register update(Register newData) {
        return Builder.updateBuilder(this, newData).build();
    }

    

    
}
