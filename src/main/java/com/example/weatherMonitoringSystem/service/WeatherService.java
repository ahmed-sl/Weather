package com.example.weatherMonitoringSystem.service;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.example.weatherMonitoringSystem.repository.WeatherRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    List<String> cities = Arrays.asList("riyadh", "jeddah", "mecca", "medina", "dammam", "khobar", "abha", "tabuk", "taif", "hail");
    private final String apiKey = "2753e6dcf6fd4750ab7115505242908";
    public String saveData() {
        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setLocation("Jeddah");
        weatherModel.setLatitude(24.45);
        weatherModel.setLongitude(24.45);
        weatherModel.setDate(LocalDate.now());
        weatherModel.setDegree(44.1);
        weatherRepository.save(weatherModel);
        return "Data save to database !!";
    }

    public List<WeatherModel> getAllData(){
        return weatherRepository.findAll();
    }

    public String fetchAndSaveWeatherData() {
        String message = null;
        for (String city : cities) {
            String url = String.format("https://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no", apiKey, city);
            try {
                String response = restTemplate.getForObject(url, String.class);
                message = parseAndSave(response, city);
            } catch (Exception e) {
                System.out.println("Error fetching weather data for city: " + city);
                e.printStackTrace();
            }
        }
        return message;
    }

    private String parseAndSave(String jsonResponse, String city) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonResponse);

        String location = root.path("location").path("name").asText();
        double latitude = root.path("location").path("lat").asDouble();
        double longitude = root.path("location").path("lon").asDouble();

        String localtimeString = root.path("location").path("localtime").asText();
        LocalDate date = LocalDate.parse(localtimeString.split(" ")[0], DateTimeFormatter.ISO_LOCAL_DATE);

        double degree = root.path("current").path("temp_c").asDouble();

        WeatherModel weatherData = new WeatherModel();
        weatherData.setLocation(location);
        weatherData.setLatitude(latitude);
        weatherData.setLongitude(longitude);
        weatherData.setDate(date);
        weatherData.setDegree(degree);

        weatherRepository.save(weatherData);
        return "Done !!";
    }

    public String addData() {

        return null;
    }
}
