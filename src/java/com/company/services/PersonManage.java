package com.company.services;

import com.company.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

public class PersonManage extends Person {
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    private Logger logger;

    public PersonManage(){}

    public PersonManage(String name,
                        String position,
                        HttpClient client,
                        HttpRequest request,
                        HttpResponse<String> response,
                        Logger logger) {
        super(name, position);
        this.client = client;
        this.request = request;
        this.response = response;
        this.logger = logger;
    }

    public void addPerson() throws IOException, InterruptedException{
        String propPath = "src/resources/config.properties";
        FileInputStream inputStream = new FileInputStream(propPath);
        Properties prop = new Properties();
        prop.load(inputStream);

        String url = prop.getProperty("urlPerson");

        logger = Logger.getLogger(String.valueOf(this.getClass()));

        var values = new HashMap<String, String>() {{
            put("name", getName());
            put("position", getPosition());
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/post"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        logger.info("Request: " + request);

        response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        logger.info("Status code: " + response.statusCode());
        logger.info("Response: " + response.body());
    }
}
