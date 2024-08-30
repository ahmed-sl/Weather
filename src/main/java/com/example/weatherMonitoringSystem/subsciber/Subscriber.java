package com.example.weatherMonitoringSystem.subsciber;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Subscriber {

    // https://api.weatherapi.com/v1/current.json?key=2753e6dcf6fd4750ab7115505242908&q=Jeddah&aqi=no

    @KafkaListener(topics = {"testTopic"})
    public void listenToKafkaTopic(String message) {
        System.out.println("Message received is " + message);
    }
}