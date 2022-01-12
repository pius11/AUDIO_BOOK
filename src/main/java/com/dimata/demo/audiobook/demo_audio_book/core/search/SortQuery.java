package com.dimata.demo.sekolah.demo_data_siswa.core.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SortQuery {
    private String sortBy;
    private boolean asc;
}
