package com.company;

import com.company.interfaces.Info;
import com.company.services.GeoLocationService;
import com.company.services.PersonManage;
import com.company.services.WeatherService;

public class Main {

    public static void main(String[] args) throws Exception{
        WeatherService weatherService = new WeatherService();
        GeoLocationService geoLocationService = new GeoLocationService();
        PersonManage personManage = new PersonManage();

        // openweathermap api not support kz zip/postal codes
        weatherService.setCityCode("100-0001");          // Tokyo/Chiyoda
        weatherService.setCountryDomain("jp");           // Japan

        geoLocationService.setCityCode("KZ-ALA");

        getInfo(weatherService);
        getInfo(geoLocationService);

        personManage.setName("Arman Baibaba");
        personManage.setPosition("my position");
        personManage.addPerson();

    }

    private static void getInfo(Info info) throws Exception{
        info.getInfo();
    }
}
