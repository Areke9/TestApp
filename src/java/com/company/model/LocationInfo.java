package com.company.model;

import com.company.interfaces.Info;

public class LocationInfo implements Info {
    private String countryDomain;
    private String cityCode;

    public LocationInfo(String cityCode, String countryDomain) {
        this.cityCode = cityCode;
        this.countryDomain = countryDomain;
    }

    public LocationInfo() {

    }

    public String getCountryDomain() {
        return countryDomain;
    }

    public void setCountryDomain(String countryDomain) {
        this.countryDomain = countryDomain;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public void getInfo() throws Exception {

    }
}
