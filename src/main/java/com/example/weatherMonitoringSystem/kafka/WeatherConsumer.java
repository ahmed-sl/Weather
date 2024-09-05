package com.example.weatherMonitoringSystem.kafka;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.example.weatherMonitoringSystem.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherConsumer {

    private final WeatherService weatherService;
    Logger logger = LoggerFactory.getLogger(WeatherConsumer.class);

    @KafkaListener(topics = {"weather"})
    public void listenToKafkaTopic(String message) {
//        weatherService.addWeatherModels(message);
        logger.trace("receive data successfully");
        System.out.println("message from kafka: " + message.toString());
    }
}