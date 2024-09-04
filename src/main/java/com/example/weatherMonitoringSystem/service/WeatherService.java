package com.example.weatherMonitoringSystem.service;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.example.weatherMonitoringSystem.repository.WeatherRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${api.key}")
    private String API_KEY;

    public List<WeatherModel> getAllData() {
        logger.trace("get all data from database");
        return weatherRepository.findAll();
    }

    public String fetchData(String city) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = LocalDate.now().format(formatter);
        double degree_avg = 0;
        logger.trace("start fetch average degree for: " + city);
        String url = String.format("http://api.weatherapi.com/v1/history.json?key=%s&q=%s&dt=%s", API_KEY, city, currentDate);
        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            degree_avg = root.path("forecast").path("forecastday").get(0).path("day").path("avgtemp_c").asDouble();
        } catch (Exception e) {
            logger.error("Error fetching weather data for city: " + city + " with message: " + e.getMessage());
        }
        return city + " average temperature = " + degree_avg;
    }

    public void addWeatherModels(WeatherModel message) {
        logger.trace("save weather model into database");
        weatherRepository.save(message);
    }
}
