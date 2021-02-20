package com.company.services;

import com.company.model.LocationInfo;

import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.logging.Logger;

public class GeoLocationService extends LocationInfo {
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    private Logger logger;

    public GeoLocationService(){}

    public GeoLocationService(HttpClient client,
                              HttpRequest request,
                              HttpResponse<String> response,
                              Logger logger) {
        this.client = client;
        this.request = request;
        this.response = response;
        this.logger = logger;
    }

    public void getInfo() throws Exception{

        String propPath = "src/resources/config.properties";
        FileInputStream inputStream = new FileInputStream(propPath);
        Properties prop = new Properties();
        prop.load(inputStream);

        String url = prop.getProperty("urlGeoLocation");
        String key = prop.getProperty("keyGeoLocation");

        logger = Logger.getLogger(String.valueOf(this.getClass()));

        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .uri(URI.create(url + getCityCode() + "&key=" + key + ""))
                .build();

        logger.info("Request: " + request.toString());

        response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        logger.info("Headers: " + response.headers());
        logger.info("Status code: " + response.statusCode());
        logger.info("Response: " + response.body());
    }
}
