package com.dimata.demo.audiobook.demo_audio_book.core.util.jackson;

import java.time.LocalDateTime;

import com.dimata.demo.audiobook.demo_audio_book.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

public class TimeDeserialize extends StdConverter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String s) {
        return FormatUtil.convertToLocalDate(s);
    }
}
