package com.dimata.demo.audiobook.demo_audio_book.forms;

import com.dimata.demo.audiobook.demo_audio_book.core.api.RecordAdapter;
import com.dimata.demo.audiobook.demo_audio_book.models.table.UserMain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserMainForm implements RecordAdapter <UserMain> {
    
    private Long id;
    private Long registerCode;
    private Long userCode;
    private Long idbook;
    
    
    @Override
    public UserMain convertNewRecord() {
        return UserMain.Builder.createNewRecord()
            .registerCode(registerCode)
            .userCode(userCode)
            .idBook(idbook)
            .id(id)
            .build();
    }
    @Override
    public UserMain convertToRecord() {
        return UserMain.Builder.emptyBuilder()
            
            .registerCode(registerCode)
            .userCode(userCode)
            .idBook(idbook)
            .id(id)
            .build();
    }
}
