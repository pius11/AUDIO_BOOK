package com.dimata.demo.audiobook.demo_audio_book.forms;
import com.dimata.demo.audiobook.demo_audio_book.core.api.RecordAdapter;
import com.dimata.demo.audiobook.demo_audio_book.models.table.UserMain;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class UserMainForm implements RecordAdapter<UserMain> {
    private Long id;
    private Long idBook;
    private Long userCode;
    private Long registerCode;
        
    
    @Override
    public UserMain convertNewRecord() {
        return UserMain.Builder.createNewRecord()
            .idBook(idBook)
            .userCode(userCode)
            .registerCode(registerCode)
            .id(id)
            .build();
    }
    @Override
    public UserMain convertToRecord() {
        return UserMain .Builder.emptyBuilder()
            
            .idBook(idBook)
            .userCode(userCode)
            .registerCode(registerCode)
            .id(id)
            .build();
    }
}
