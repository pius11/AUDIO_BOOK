package com.dimata.demo.audiobook.demo_audio_book.core.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FunctionOpt {
    String function;

    @Override
    public String toString(){
        return function;
    }
}
