package com.dimata.demo.audiobook.demo_audio_book.forms;

import java.time.LocalDate;

import com.dimata.demo.audiobook.demo_audio_book.core.api.RecordAdapter;
import com.dimata.demo.audiobook.demo_audio_book.core.util.jackson.DateDeserialize;
import com.dimata.demo.audiobook.demo_audio_book.models.table.Register;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterForm implements RecordAdapter<Register> {

    private Long id;
    private String email;
    private String password;
    @JsonDeserialize(converter = DateDeserialize.class)
    private LocalDate birthDate;
    @Override
    public Register convertNewRecord() {
        return Register.Builder.createNewRecord(email, password)
            .birthDate(birthDate)
            .id(id)
            .build();
    }
    @Override
    public Register convertToRecord() {
        return Register.Builder.emptyBuilder()
            .birthDate(birthDate)
            .password(password)
            .email(email)
            .id(id)
            .build();
    }
}
