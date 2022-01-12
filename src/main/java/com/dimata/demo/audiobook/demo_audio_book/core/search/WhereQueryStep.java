package com.dimata.demo.sekolah.demo_data_siswa.core.search;

public interface WhereQueryStep {
    String result();
    WhereQueryStep and(WhereQueryStep step);
    WhereQueryStep or(WhereQueryStep step);
    WhereQueryStep not(WhereQueryStep step);
}
