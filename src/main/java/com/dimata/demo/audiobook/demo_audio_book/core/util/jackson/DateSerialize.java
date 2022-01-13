package com.dimata.demo.audiobook.demo_audio_book.core.util.jackson;

import java.time.LocalDate;

import com.dimata.demo.audiobook.demo_audio_book.core.util.FormatUtil;
import com.fasterxml.jackson.databind.util.StdConverter;

public class DateSerialize extends StdConverter<LocalDate, String> {
    @Override
    public String convert(LocalDate time) {
        return FormatUtil.convertDateToString(time);
    }
}
