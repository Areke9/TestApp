package com.company.services;

import com.company.model.LocationInfo;

import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.logging.Logger;

public class WeatherService extends LocationInfo {
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    private Logger logger;

    public WeatherService(){}

    public WeatherService(HttpClient client,
                          HttpRequest request,
                          HttpResponse<String> response,
                          Logger logger) {
        this.client = client;
        this.request = request;
        this.response = response;
        this.logger = logger;
    }

    @Override
    public void getInfo() throws Exception{
        String propPath = "src/resources/config.properties";
        FileInputStream inputStream = new FileInputStream(propPath);
        Properties prop = new Properties();
        prop.load(inputStream);

        String url = prop.getProperty("urlWeather");
        String key = prop.getProperty("keyWeather");

        logger = Logger.getLogger(String.valueOf(this.getClass()));

        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .uri(URI.create(url
                        + getCityCode() + ","
                        + getCountryDomain() + "&appid="
                        + key + "&units=metric"))
                .build();

        logger.info("Request: " + request.toString());

        response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        logger.info("Headers: " + response.headers());
        logger.info("Status code: " + response.statusCode());
        logger.info("Response: " + response.body());
    }
}
