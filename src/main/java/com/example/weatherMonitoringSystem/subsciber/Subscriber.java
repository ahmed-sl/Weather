package com.example.weatherMonitoringSystem.subsciber;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Subscriber {

    @KafkaListener(topics = {"weather"})
    public void listenToKafkaTopic(List<WeatherModel> message) {
        System.out.println("Message received is " + message.toString());
    }
}