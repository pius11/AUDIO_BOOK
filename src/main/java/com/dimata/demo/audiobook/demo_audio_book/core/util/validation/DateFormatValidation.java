package com.dimata.demo.audiobook.demo_audio_book.core.util.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dimata.demo.audiobook.demo_audio_book.core.util.CheckUtil;

public class DateFormatValidation implements ConstraintValidator<DateFormatVal, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return CheckUtil.isDateFormatValid(value);
    }
}
