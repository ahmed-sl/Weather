package com.example.weatherMonitoringSystem.kafka;

import com.example.weatherMonitoringSystem.api.model.WeatherDataResponse;
import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

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
}
