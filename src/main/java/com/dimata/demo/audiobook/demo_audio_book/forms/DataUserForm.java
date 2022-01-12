package com.dimata.demo.sekolah.demo_audio_book.forms;

import java.time.LocalDate;

import com.dimata.demo.sekolah.demo_audio_book.core.api.RecordAdapter;
import com.dimata.demo.sekolah.demo_audio_book.core.util.jackson.DateDeserialize;
import com.dimata.demo.sekolah.demo_audio_book.models.table.DataUser;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DataUserForm implements RecordAdapter<DataUser>{
    private Long id;
    private String email;
    private String displayname;
    private String username;
    private String phonenum;

    @JsonDeserialize(converter = DateDeserialize.class)
    private LocalDate birthDate;
    @Override
    public DataUser convertNewRecord() {
        return DataUser.Builder.createNewRecord(email)
            .birthDate(birthDate)
            .displayname(displayname)
            .username(username)
            .phonenum(phonenum)
            .id(id)
            .build();
    }
    @Override
    public DataUser convertToRecord() {
        return DataUser.Builder.emptyBuilder()
            .birthDate(birthDate)
            .email(email)
            .displayname(displayname)
            .username(username)
            .phonenum(phonenum)
            .id(id)
            .build();
    }
}
