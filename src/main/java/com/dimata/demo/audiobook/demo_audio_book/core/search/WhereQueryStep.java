package com.dimata.demo.audiobook.demo_audio_book.core.search;

public interface WhereQueryStep {
    String result();
    WhereQueryStep and(WhereQueryStep step);
    WhereQueryStep or(WhereQueryStep step);
    WhereQueryStep not(WhereQueryStep step);
}
