package com.dimata.demo.sekolah.demo_data_siswa.enums;

import lombok.Getter;

public enum GenderSiswa {
    LAKI_LAKI(0),
    PEREMPUAN(1),
    UNDEFINED(-1);

    @Getter
    private final int code;

    public static GenderSiswa getGender(Integer code){
        switch (code) {
            case 0:
                return LAKI_LAKI;
            case 1:
                return PEREMPUAN;
            default:
                return UNDEFINED;
        }
    }

    GenderSiswa(int code) {
        this.code = code;
    }

    public String parseGender(GenderSiswa gender){
        if (gender == GenderSiswa.LAKI_LAKI) {
            return "Laki-Laki";
        }
        return "Perempuan";
    }

    public String parseGender(int code) {
        if (getGender(code) == GenderSiswa.LAKI_LAKI) {
            return "Laki-Laki";
        } else if (getGender(code) == GenderSiswa.PEREMPUAN) {
            return "Perempuan";
        }
        return "Undefined";
    }
}
