package com.example.weatherMonitoringSystem.kafka;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublishWeatherData {

    Logger logger = LoggerFactory.getLogger(PublishWeatherData.class);

    private final KafkaTemplate<String, WeatherModel> kafkaTemplate;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final List<String> cities = Arrays.asList("riyadh", "jeddah", "mecca", "medina", "dammam", "khobar", "abha", "tabuk", "taif", "hail");
    @Value("${api.key}")
    private String API_KEY;

    public void publishWeatherData(WeatherModel weatherModels) {
        kafkaTemplate.send("weather", weatherModels);
        logger.trace("publish data successfully");
    }


    @Scheduled(fixedRate = 10000)
    public void fetchData() {
        List<WeatherModel> weatherModels = new ArrayList<>();
        int id = 1;
        for (String city : cities) {
            logger.trace("start fetch data for: " + city);
            String url = String.format("https://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no", API_KEY, city);
            try {
                String response = restTemplate.getForObject(url, String.class);
                publishWeatherData(parseData(response, id));
            } catch (Exception e) {
                logger.error("Error fetching weather data for city: " + city + " with message: " + e.getMessage());
            }
            id++;
        }
    }

    private WeatherModel parseData(String jsonResponse, int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = objectMapper.readTree(jsonResponse);
        } catch (JsonProcessingException e) {
            logger.error("mapper Error with message: " + e.getMessage());
        }

        assert root != null;
        String location = root.path("location").path("name").asText();
        double latitude = root.path("location").path("lat").asDouble();
        double longitude = root.path("location").path("lon").asDouble();

        String localtimeString = root.path("location").path("localtime").asText();
        LocalDate date = LocalDate.parse(localtimeString.split(" ")[0], DateTimeFormatter.ISO_LOCAL_DATE);

        double degree = root.path("current").path("temp_c").asDouble();

        WeatherModel weatherData = new WeatherModel();
        weatherData.setId(id);
        weatherData.setLocation(location);
        weatherData.setLatitude(latitude);
        weatherData.setLongitude(longitude);
        weatherData.setDate(date);
        weatherData.setDegree(degree);

        logger.trace("convert JSON message to Weather model");
        return weatherData;
    }
}
