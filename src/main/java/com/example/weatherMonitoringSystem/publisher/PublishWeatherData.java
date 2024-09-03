package com.example.weatherMonitoringSystem.publisher;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublishWeatherData {

    private final KafkaTemplate<String, List<WeatherModel>> kafkaTemplate;

    public void publishWeatherData(List<WeatherModel> weatherModels){
        System.out.printf(weatherModels.toString());
        kafkaTemplate.send("weather",weatherModels);
    }
}
