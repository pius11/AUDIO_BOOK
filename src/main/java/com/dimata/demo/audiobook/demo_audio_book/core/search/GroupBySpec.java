package com.dimata.demo.audiobook.demo_audio_book.core.search;

public interface GroupBySpec {
    GroupBySpec merge(GroupBySpec groupBy);
    GroupBySpec append(String group);
    String getQuery();
}
