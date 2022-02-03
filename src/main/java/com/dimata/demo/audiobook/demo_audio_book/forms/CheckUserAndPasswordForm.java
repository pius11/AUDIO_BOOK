package com.dimata.demo.audiobook.demo_audio_book.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckUserAndPasswordForm {
    private String email;
    private String password;
}
