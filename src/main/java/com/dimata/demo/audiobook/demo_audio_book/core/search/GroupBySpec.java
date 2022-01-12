package com.dimata.demo.sekolah.demo_data_siswa.core.search;

public interface GroupBySpec {
    GroupBySpec merge(GroupBySpec groupBy);
    GroupBySpec append(String group);
    String getQuery();
}
