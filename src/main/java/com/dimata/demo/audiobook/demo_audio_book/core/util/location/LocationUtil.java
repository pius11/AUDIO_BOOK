package com.dimata.demo.sekolah.demo_data_siswa.core.util.location;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonIgnoreType
public class LocationUtil {

    private LocationUtilSpec location;

    public boolean isSubDistrictLevel() {
        return location.isCountryExist() 
            && location.isProvinceExist()
            && location.isDistrictExist()
            && location.isSubDistrictExist();
    }

    public boolean isDistrictLevel() {
        return location.isCountryExist()
            && location.isProvinceExist()
            && location.isDistrictExist()
            && !location.isSubDistrictExist();
    }

    public boolean isProvinceLevel() {
        return location.isCountryExist()
            && location.isProvinceExist()
            && !location.isDistrictExist()
            && !location.isSubDistrictExist();
    }

    public boolean isCountryLevel() {
        return location.isCountryExist()
            && !location.isProvinceExist()
            && !location.isDistrictExist()
            && !location.isSubDistrictExist();
    }

    public boolean isLocationOrderCorrect() {
        return location.isCountryExist()
            || location.isProvinceExist()
            || location.isDistrictExist()
            || location.isSubDistrictExist();
    }


    
}
