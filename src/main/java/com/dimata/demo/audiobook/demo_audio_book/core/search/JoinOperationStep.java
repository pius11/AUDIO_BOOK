package com.dimata.demo.audiobook.demo_audio_book.core.search;

public interface JoinOperationStep {
    JoinQueryStep on(WhereQueryStep operationStep);
}
